import xml.etree.ElementTree as ET
from collections import Counter
import sys

def count_tags(file_path):
    # Parse the XML file
    tree = ET.parse(file_path)
    root = tree.getroot()

    # Namespace handling for XML
    namespace = {"ns": "http://pmd.sourceforge.net/ruleset/2.0.0"}

    # Find all tag properties
    tags = []
    for prop in root.findall(".//ns:property[@name='tags']", namespace):
        value = prop.attrib.get('value', "")
        tags.extend(value.split(','))  # Split the tags by ','

    # Count the occurrences of each tag
    tag_counts = Counter(tags)

    # Print the summary
    print("Tag counts:")
    for tag, count in tag_counts.items():
        print(f"{tag}: {count}")

    print("\nTotal tags:", len(tags))
    print("Unique tags:", len(tag_counts))


if __name__ == "__main__":
    if len(sys.argv) != 2:
        print("Usage: python count_tags.py <path_to_jpinpoint_rules.xml>")
        sys.exit(1)

    file_path = sys.argv[1]
    count_tags(file_path)