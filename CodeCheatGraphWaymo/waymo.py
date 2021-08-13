import math

def canReach(height, width, obstacle, r, start, end):
    '''
    :param height:
    :param width:
    :param obstacles: list of [(obstacle_x,obstacle_y)...]
    :param r: size of circle obstacles
    :param start: (start_x, start,y)
    :param end:  (end_x, end_y)
    :return:
    '''

    '''
    (x - x_circle) ^ 2 + (y - y_circle) ^ <= 0
    
    '''
    grid_size = 1

    h = height // grid_size
    w = width // grid_size

    start_coord_i = math.floor(start_y // grid_size) - 1
    start_coord_j = math.floor(start_x // grid_size) - 1

    end_coord_i = math.floor(end_y // grid_size) - 1
    end_coord_j = math.floor(end_x // grid_size) - 1

    grid_map = [[1] * w for _ in range(h)]

    def get_4_coords(i,j):
        return [
            (i * grid_size, j * grid_size),
            (i * grid_size + i, j * grid_size + j),
            (i * grid_size, j * grid_size + j),
            (i * grid_size + i, j * grid_size)

        ]

    for cur_i in range(h):
        for cur_j in range(w):
            four_coords = get_4_coords(cur_i, cur_j)
            cover = False
            for x, y in four_coords:
                if (x - obstacle_x) ** 2 + (y - obstacle_y) < 0:
                    cover = True
                    break
            grid_map[h - cur_i][cur_j] = 0 # obstacle

    grid_map[h - start_coord_i][start_coord_j] = 100 # start
    grid_map[h - end_coord_i][end_coord_j] = -100 #end

    m= h - start_coord_i
    n = start_coord_j

    p = h - end_coord_i
    q = end_coord_j

    visited = set()
    def dfs(grid_map, i, j, visited):
        visited.add((i,j))
        if i == p and j == q:
            return True
        else:
            dirs = [.....]
            for dir in dirs:
                # check if 8 connected 对角缝隙
                dfs
