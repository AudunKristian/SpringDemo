import os
import json

# Define the root directory where the script is located
project_root = os.path.dirname(os.path.abspath(__file__))

# Define directories to exclude from traversal
exclude_dirs = ['venv', '__pycache__', 'node_modules', 'frontend']

# Define file types to include
include_extensions = ['.py']

# Helper function to determine if a directory should be excluded
def should_exclude_dir(dir_path):
    return any(exclude_dir in dir_path for exclude_dir in exclude_dirs)

# Helper function to gather useful files
def gather_useful_files(root_dir):
    useful_files = []
    for dirpath, _, filenames in os.walk(root_dir):
        if should_exclude_dir(dirpath):
            continue
        for filename in filenames:
            if any(filename.endswith(ext) for ext in include_extensions):
                file_path = os.path.join(dirpath, filename)
                useful_files.append(file_path)
    return useful_files

# Gather useful files
files = gather_useful_files(project_root)

# Collect information about each file
file_info = {}
for file_path in files:
    with open(file_path, 'r', encoding='utf-8', errors='ignore') as file:
        content = file.read()
        file_info[file_path] = {
            'content': content[:1000],  # Show only the first 1000 characters
            'size': os.path.getsize(file_path)
        }

# Save the collected information to a JSON file
output_file = 'file_info.json'
with open(output_file, 'w', encoding='utf-8') as f:
    json.dump(file_info, f, indent=4)

print(f"Useful files information saved to {output_file}")
