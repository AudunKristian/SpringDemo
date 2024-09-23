import os

def get_filenames(directory):
    try:
        filenames = os.listdir(directory)
        return filenames
    except FileNotFoundError:
        print(f"The directory {directory} does not exist.")
        return []

if __name__ == "__main__":
    directory = "Pictures"
    filenames = get_filenames(directory)
    for filename in filenames:
        print(filename)