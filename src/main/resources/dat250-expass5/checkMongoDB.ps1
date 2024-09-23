# Read the signature hash from the .sha256 file and extract the first 64 characters, converting to uppercase
$sigHash = (Get-Content '.\mongodb-windows-x86_64-7.0.14-signed.msi.sha256' | Out-String).SubString(0,64).ToUpper()
# Calculate the file hash of the .msi file
$fileHash = (Get-FileHash '.\mongodb-windows-x86_64-7.0.14-signed.msi').Hash.Trim()

# Output both hashes for comparison
echo $sigHash
echo $fileHash

# Compare the two hashes to check if they are equal
$sigHash -eq $fileHash
