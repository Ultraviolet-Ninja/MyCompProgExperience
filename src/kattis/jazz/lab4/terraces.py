# Jarred Kohout, Bilal Syed, Malcolm Johnson

from collections import deque


# quick helper function to check bounds of a cell
def in_grid(cell, x, y):
    return 0 <= cell[0] < x and 0 <= cell[1] < y


# true if terrace is poolable, false otherwise
# Poolable in this case is that for every cell in the terrace, all surrounding cells that aren't in the terrace
# have a greater value than the terrace's value
# In other words, if any neighboring cell outside the terrace has a lesser value, the terrace is not poolable
# since it can flow to that cell
def can_pool(terrace, grid, x, y):
    for cell in terrace:
        i = cell[0]
        j = cell[1]
        value = grid[i][j]  # all cells in a terrace have the same value, but it is easier to just reassign each time
        left = (i-1, j)
        right = (i+1, j)
        up = (i, j-1)
        down = (i, j+1)
        for neighbor in (left, right, up, down):
            if neighbor not in terrace and in_grid(neighbor, x, y):
                # if any neighbor is less, none in terrace can pool
                if grid[neighbor[0]][neighbor[1]] < value:
                    return False
    return True


# construct a terrace where each terrace is a connected component, meaning that all cells in the terrace are immediate
# neighbors to at least one other cell in the terrace, and that all cells in the terrace have the # same value
def get_terrace(grid, x, y, cell, searched):
    # perform a naive search starting on the given cell
    terrace = set()
    frontier = deque()
    frontier.appendleft(cell)
    searched.add(cell)
    value = grid[cell[0]][cell[1]]  # only add cells to terrace that have this value
    while len(frontier) > 0:
        cell = frontier.pop()
        terrace.add(cell)
        i = cell[0]
        j = cell[1]
        left = (i-1, j)
        right = (i+1, j)
        up = (i, j-1)
        down = (i, j+1)
        for neighbor in (left, right, up, down):
            # add (valid) neighbor cell to terrace if it has the same value and isn't already part of another terrace
            if in_grid(neighbor, x, y) and neighbor not in searched and value == grid[neighbor[0]][neighbor[1]]:
                searched.add(neighbor)
                frontier.appendleft(neighbor)
    return terrace


# construct the list of terraces
def get_terraces(grid, x, y):
    terraces = []
    searched = set()
    for i in range(x):
        for j in range(y):
            cell = (i, j)
            # if cell is in searched, it already belongs to a terrace, so we skip it
            if cell not in searched:
                # do search on cell to construct terrace
                terrace = get_terrace(grid, x, y, cell, searched)
                terraces.append(terrace)

    return terraces


# print out the number of farmable locations in the grid
def analyze(grid, x, y):
    num_poolable = 0
    # generate terraces (each connected component)
    terraces = get_terraces(grid, x, y)
    for terrace in terraces:
        if can_pool(terrace, grid, x, y):
            num_poolable += len(terrace)

    print(num_poolable)


# main - run for one grid
def main():
    width, height = (int(x) for x in input().split())
    grid = []
    for _ in range(width):
        grid.append([])
    # populate grid
    for h in range(height):
        for idx, i in enumerate(int(x) for x in input().split()):
            grid[idx].append(i)

    # access cells with grid[x][y], or grid[width][height]

    # determine and print number of farmable spots
    analyze(grid, width, height)


if __name__ == '__main__':
    main()
