import { MongoClient } from 'mongodb';

async function run() {
  const uri = "mongodb://localhost:27017/"; // Replace with your MongoDB connection string
  const client = new MongoClient(uri);

  try {
    await client.connect();
    const database = client.db('Test'); // Switch to your database
    const inventory = database.collection('inventory');

    // Insert a single document
    await inventory.insertOne({
      item: 'canvas',
      qty: 100,
      tags: ['cotton'],
      size: { h: 28, w: 35.5, uom: 'cm' }
    });

    // Insert multiple documents
    await inventory.insertMany([
      { item: 'journal', qty: 25, tags: ['blank', 'red'], size: { h: 14, w: 21, uom: 'cm' } },
      { item: 'mat', qty: 85, tags: ['gray'], size: { h: 27.9, w: 35.5, uom: 'cm' } },
      { item: 'mousepad', qty: 25, tags: ['gel', 'blue'], size: { h: 19, w: 22.85, uom: 'cm' } }
    ]);
  } finally {
    await client.close();
  }
}

run().catch(console.dir);