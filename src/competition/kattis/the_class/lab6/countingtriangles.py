from itertools import combinations

class Line:
    """
    A Line is a pair of (x,y) coordinates that correlate to the ends of the line
    """
    def __init__(self, str_array):
        self.x1 = float(str_array[0])
        self.y1 = float(str_array[1])
        self.x2 = float(str_array[2])
        self.y2 = float(str_array[3])

    def __str__(self):
        return f"({self.x1}, {self.y1}) - ({self.x2}, {self.y2})"

    @staticmethod
    def ccw(val1, val2, val3, val4, val5, val6):
        """
        Code cited here: https://bryceboe.com/2006/10/23/line-segment-intersection-algorithm/


        :param val1: The first x coordinate
        :param val2: The first y coordinate
        :param val3: The second x coordinate
        :param val4: The second y coordinate
        :param val5: The third x coordinate
        :param val6: The third y coordinate
        :return: True to show that a point intersects a line
        """
        return (val6 - val2) * (val3 - val1) > (val4 - val2) * (val5 - val1)


    def does_intersect(self, other_line):
        """

        :param other_line: The other line to check an intersection between
        :return: True if there's an intersection between the 2 lines
        """
        return Line.ccw(self.x1, self.y1, other_line.x1, other_line.y1, other_line.x2, other_line.y2) != Line.ccw(self.x2, self.y2, other_line.x1, other_line.y1, other_line.x2, other_line.y2) and \
    Line.ccw(self.x1, self.y1, self.x2, self.y2, other_line.x1, other_line.y1) != Line.ccw(self.x1, self.y1, self.x2, self.y2, other_line.x2, other_line.y2)


def are_all_connected(line1, line2, line3):
    """
    Determines whether the lines given are connected to form a triangle by seeing if they intersect with
    each other

    :param line1: The first line
    :param line2: The second line
    :param line3: The third
    :return: True if all the lines are connected to form a triangle
    """
    return line1.does_intersect(line2) and line1.does_intersect(line3) and line2.does_intersect(line3)


def analyze(combo_set):
    count = 0

    for combo in combo_set:
        if are_all_connected(*combo):
            count += 1
    print(count)


def main():
    line_count = int(input())
    all_line_groups = []
    group = []
    # Gathering all the inputs and turn them into lines objects
    while line_count != 0:
        for _ in range(line_count):
            group.append(Line(input().split()))
        all_line_groups.append(group)
        group = []
        line_count = int(input())

    # Creating the combinations with an r = 3 for a set of elements
    # Then, analyzing the combination set to find the combinations that form a triangle
    for line_group in all_line_groups:
        analyze(combinations(line_group, 3))


if __name__ == '__main__':
    main()