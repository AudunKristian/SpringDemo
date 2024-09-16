import os
"""
def list_files(startpath='.'):
    for root, dirs, files in os.walk(startpath):
        level = root.replace(startpath, '').count(os.sep)
        indent = ' ' * 4 * (level)
        print(f'{indent}{os.path.basename(root)}/')
        subindent = ' ' * 4 * (level + 1)
        for f in files:
            print(f'{subindent}{f}')

# Run the function starting from the current directory
list_files()
"""



def list_files(startpath='.'):
    """
    Lists the directory and file structure starting from startpath.
    Excludes auto-generated files and directories.

    Args:
    startpath (str): The root directory to start traversal.
    """
    # Patterns for auto-generated files and directories to exclude
    exclude_dirs = {'node_modules', 'build', '.git', '.gradle', '.vscode', 'coverage'}
    exclude_files = {'package-lock.json', 'yarn.lock'}

    for root, dirs, files in os.walk(startpath):
        # Check if we are in the 'frontend' directory
        if 'frontend' in root:
            # If in 'frontend', stop traversing into subdirectories
            if 'frontend/src' in root:
                # Clear the dirs list to avoid traversing further into 'frontend/src'
                dirs[:] = []
            else:
                # Otherwise, if we are in 'frontend' but not 'frontend/src', clear the files list
                files[:] = []
        
        # Remove directories and files that match the exclude patterns
        dirs[:] = [d for d in dirs if d not in exclude_dirs]
        files[:] = [f for f in files if f not in exclude_files]

        # Print the directory structure
        level = root.replace(startpath, '').count(os.sep)
        indent = ' ' * 4 * (level)
        print(f'{indent}{os.path.basename(root)}/')
        subindent = ' ' * 4 * (level + 1)
        for f in files:
            print(f'{subindent}{f}')

# Run the function starting from the current directory
list_files()

