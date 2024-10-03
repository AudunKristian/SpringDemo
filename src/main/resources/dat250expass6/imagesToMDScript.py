import os

"""
This script scans a specified directory for PNG files, sorts them by their creation time,
and generates a Markdown file listing these images in the sorted order.
Functions:
    None
Variables:
    directory (str): The directory where the PNG files are located.
    output_md_file (str): The name of the output Markdown file.
    png_files (list): A list to store tuples of creation time and filenames of PNG files.
Workflow:
    1. The script lists all files in the specified directory.
    2. It filters out files that do not have a '.png' extension.
    3. For each PNG file, it retrieves the creation time and stores it in a list along with the filename.
    4. The list of PNG files is sorted by creation time.
    5. The script writes the sorted list of PNG files to the output Markdown file in Markdown image syntax.
Output:
    A Markdown file named 'output.md' containing the sorted list of PNG files with their respective paths.
Usage:
    Ensure the 'directory' variable points to the correct directory containing PNG files.
    Run the script to generate the 'output.md' file.

"""

# Directory where your PNG files are located
directory = 'src/main/resources/dat250expass6/Pictures'

# Output Markdown file
output_md_file = 'output.md'

# Gather all PNG files with their creation time
png_files = []
for filename in os.listdir(directory):
    if filename.endswith('.png'):
        # Get the full path to the file
        file_path = os.path.join(directory, filename)
        # Append a tuple (creation time, filename) to the list
        png_files.append((os.path.getctime(file_path), filename))   ## Different command also working:  png_files.append((os.stat(file_path).st_ctime, filename))

# Sort the files by creation time (first element of the tuple)
png_files.sort()

# Write the sorted PNG files to the Markdown file
with open(output_md_file, 'w') as md_file:
    for _, filename in png_files:
        # Create the Markdown image syntax
        md_image_syntax = f"![{filename}]({os.path.join(directory, filename)})\n"
        # Write the image syntax to the Markdown file
        md_file.write(md_image_syntax)

print(f"All PNG files (sorted by creation time) have been added to {output_md_file}")






