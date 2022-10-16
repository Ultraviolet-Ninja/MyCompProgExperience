
class Row:
    """
    Holds width and height for a row of rectangles
    """
    def __init__(self):
        self.width = 0
        self.height = 0


def calculate_layout(max_width, widths, heights):
    """Calculate dimensions of each row, then compile these dimensions to calculate the window layout.

    :param max_width: maximum width of window
    :param widths: widths of rectangles
    :param heights: heights of rectangles
    :return: (window width, window height)
    """
    rows = [Row()]  # start with one row, and always operate on most recent row (rows[-1])
    for width, height in zip(widths, heights):
        if rows[-1].width + width > max_width:
            # exceeds max width => need a new row
            rows.append(Row())
        # at this point, we know the width fits
        rows[-1].width += width
        # change height of row if rectangle height is larger
        rows[-1].height = max(rows[-1].height, height)

    win_width = max(row.width for row in rows)
    win_height = sum(row.height for row in rows)

    return win_width, win_height


def flow_layout(max_width):
    """Read input to parse dimensions and calculate layout for one window, printing result to stdout

    :param max_width: maximum width allowed for this set of rectangles
    """
    widths = []
    heights = []
    width, height = (int(x) for x in input().split())
    while width > -1:
        widths.append(width)
        heights.append(height)
        width, height = (int(x) for x in input().split())

    win_width, win_height = calculate_layout(max_width, widths, heights)
    print(f'{win_width} x {win_height}')


def main():
    """Read input to call flow_layout once per window
    """
    max_width = int(input())
    while max_width != 0:
        flow_layout(max_width)
        max_width = int(input())


if __name__ == '__main__':
    main()
