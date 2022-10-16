from itertools import combinations

class Line:
    def __init__(self, str_array):
        self.x1 = float(str_array[0])
        self.y1 = float(str_array[1])
        self.x2 = float(str_array[2])
        self.y2 = float(str_array[3])

    def __str__(self):
        return f"({self.x1}, {self.y1}) - ({self.x2}, {self.y2})"

    @staticmethod
    def ccw(val1, val2, val3, val4, val5, val6):
        return (val6 - val2) * (val3 - val1) > (val4 - val2) * (val5 - val1)


    def does_intersect(self, other_line):
        return Line.ccw(self.x1, self.y1, other_line.x1, other_line.y1, other_line.x2, other_line.y2) != Line.ccw(self.x2, self.y2, other_line.x1, other_line.y1, other_line.x2, other_line.y2) and \
    Line.ccw(self.x1, self.y1, self.x2, self.y2, other_line.x1, other_line.y1) != Line.ccw(self.x1, self.y1, self.x2, self.y2, other_line.x2, other_line.y2)


def are_all_connected(line1, line2, line3):
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
    combinations_list = []
    while line_count != 0:
        for _ in range(line_count):
            group.append(Line(input().split()))
        all_line_groups.append(group)
        group = []
        line_count = int(input())

    # for line_group in all_line_groups:
    #     analyze(combinations(line_group, 3))

    for line_group in all_line_groups:
        combinations_list.append(combinations(line_group, 3))

    for set in combinations_list:
        analyze(set)


if __name__ == '__main__':
    main()