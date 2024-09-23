// Connect to your MongoDB instance
// Assuming you have mongosh connected to your MongoDB instance.

// Step 1: Create the sample collection `orders` with the provided documents.
db.orders.insertMany([
  { _id: 1, cust_id: "Ant O. Knee", ord_date: new Date("2020-03-01"), price: 25, items: [ { sku: "oranges", qty: 5, price: 2.5 }, { sku: "apples", qty: 5, price: 2.5 } ], status: "A" },
  { _id: 2, cust_id: "Ant O. Knee", ord_date: new Date("2020-03-08"), price: 70, items: [ { sku: "oranges", qty: 8, price: 2.5 }, { sku: "chocolates", qty: 5, price: 10 } ], status: "A" },
  { _id: 3, cust_id: "Busby Bee", ord_date: new Date("2020-03-08"), price: 50, items: [ { sku: "oranges", qty: 10, price: 2.5 }, { sku: "pears", qty: 10, price: 2.5 } ], status: "A" },
  { _id: 4, cust_id: "Busby Bee", ord_date: new Date("2020-03-18"), price: 25, items: [ { sku: "oranges", qty: 10, price: 2.5 } ], status: "A" },
  { _id: 5, cust_id: "Busby Bee", ord_date: new Date("2020-03-19"), price: 50, items: [ { sku: "chocolates", qty: 5, price: 10 } ], status: "A"},
  { _id: 6, cust_id: "Cam Elot", ord_date: new Date("2020-03-19"), price: 35, items: [ { sku: "carrots", qty: 10, price: 1.0 }, { sku: "apples", qty: 10, price: 2.5 } ], status: "A" },
  { _id: 7, cust_id: "Cam Elot", ord_date: new Date("2020-03-20"), price: 25, items: [ { sku: "oranges", qty: 10, price: 2.5 } ], status: "A" },
  { _id: 8, cust_id: "Don Quis", ord_date: new Date("2020-03-20"), price: 75, items: [ { sku: "chocolates", qty: 5, price: 10 }, { sku: "apples", qty: 10, price: 2.5 } ], status: "A" },
  { _id: 9, cust_id: "Don Quis", ord_date: new Date("2020-03-20"), price: 55, items: [ { sku: "carrots", qty: 5, price: 1.0 }, { sku: "apples", qty: 10, price: 2.5 }, { sku: "oranges", qty: 10, price: 2.5 } ], status: "A" },
  { _id: 10, cust_id: "Don Quis", ord_date: new Date("2020-03-23"), price: 25, items: [ { sku: "oranges", qty: 10, price: 2.5 } ], status: "A" }
]);

// Step 2: Perform the map-reduce operation to return the total price per customer.

// Define the map function.
var mapFunction1 = function() {
  emit(this.cust_id, this.price);
};

// Define the reduce function.
var reduceFunction1 = function(keyCustId, valuesPrices) {
  return Array.sum(valuesPrices);
};

// Execute the map-reduce operation.
db.orders.mapReduce(
  mapFunction1,
  reduceFunction1,
  { out: "map_reduce_example" }
);

// Query the map-reduce results.
print("Map-Reduce Results (Total Price Per Customer):");
db.map_reduce_example.find().sort({ _id: 1 }).forEach(printjson);

// Step 3: Aggregation pipeline alternative for the above map-reduce.
db.orders.aggregate([
  { $group: { _id: "$cust_id", value: { $sum: "$price" } } },
  { $out: "agg_alternative_1" }
]);

// Query the aggregation results.
print("Aggregation Pipeline Results (Total Price Per Customer):");
db.agg_alternative_1.find().sort({ _id: 1 }).forEach(printjson);

// Step 4: Perform the map-reduce operation to calculate order and total quantity with average quantity per item.

// Define the map function.
var mapFunction2 = function() {
  for (var idx = 0; idx < this.items.length; idx++) {
    var key = this.items[idx].sku;
    var value = { count: 1, qty: this.items[idx].qty };
    emit(key, value);
  }
};

// Define the reduce function.
var reduceFunction2 = function(keySKU, countObjVals) {
  var reducedVal = { count: 0, qty: 0 };
  for (var idx = 0; idx < countObjVals.length; idx++) {
    reducedVal.count += countObjVals[idx].count;
    reducedVal.qty += countObjVals[idx].qty;
  }
  return reducedVal;
};

// Define the finalize function.
var finalizeFunction2 = function(key, reducedVal) {
  reducedVal.avg = reducedVal.qty / reducedVal.count;
  return reducedVal;
};

// Execute the map-reduce operation with a query filter and finalizer.
db.orders.mapReduce(
  mapFunction2,
  reduceFunction2,
  {
    out: { merge: "map_reduce_example2" },
    query: { ord_date: { $gte: new Date("2020-03-01") } },
    finalize: finalizeFunction2
  }
);

// Query the map-reduce results.
print("Map-Reduce Results (Average Quantity Per Item):");
db.map_reduce_example2.find().sort({ _id: 1 }).forEach(printjson);

// Step 5: Aggregation pipeline alternative for the above map-reduce operation.
db.orders.aggregate([
  { $match: { ord_date: { $gte: new Date("2020-03-01") } } },
  { $unwind: "$items" },
  { $group: { _id: "$items.sku", qty: { $sum: "$items.qty" }, orders_ids: { $addToSet: "$_id" } } },
  { $project: { value: { count: { $size: "$orders_ids" }, qty: "$qty", avg: { $divide: ["$qty", { $size: "$orders_ids" }] } } } },
  { $merge: { into: "agg_alternative_3", on: "_id", whenMatched: "replace", whenNotMatched: "insert" } }
]);

// Query the aggregation results.
print("Aggregation Pipeline Results (Average Quantity Per Item):");
db.agg_alternative_3.find().sort({ _id: 1 }).forEach(printjson);
