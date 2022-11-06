// Jarred Kohout, Bilal Syed, Malcolm Johnson

#include <stdio.h>
#include <stdlib.h>


// pop from top in stack, from bottom in queue, from position zero in priority queue
struct stack_queue {
	int * array;
	int bottom;
	int top;
};

// add element to the top of the stack
void stack_push(struct stack_queue * sq, int value) {
	sq->top += 1;
	int idx = sq->top;
	sq->array[idx] = value;
}

// remove element from the top of the stack
int stack_pop(struct stack_queue * sq) {
	int idx = sq->top;
	sq->top -= 1;
	return sq->array[idx];
}

// add element to the top of the queue
void queue_push(struct stack_queue * sq, int value) {
	sq->top += 1;
	int idx = sq->top;
	sq->array[idx] = value;
}

// remove element from the bottom of the queue
int queue_pop(struct stack_queue * sq) {
	int idx = sq->bottom;
	sq->bottom += 1;
	return sq->array[idx];
}

// push element to priority queue
void pqueue_push(struct stack_queue * sq, int value) {
    // add element to top
	sq->top += 1;
	int idx = sq->top;
	sq->array[idx] = value;

	// sift up
	int parent_idx = (idx - 1) / 2;
	while (parent_idx >= 0 && sq->array[parent_idx] < sq->array[idx]) { // while parent is less than current child
        // swap parent and child
		int temp = sq->array[parent_idx];
		sq->array[parent_idx] = sq->array[idx];
		sq->array[idx] = temp;

        // do the same for the parent
		idx = parent_idx;
		parent_idx = (idx - 1) / 2;
	}
}

// pop max element from priority queue
int pqueue_pop(struct stack_queue * sq) {
    // get the max element (element at position 0)
	int ret_val = sq->array[0];
    // set the root value to the value found at the end of the heap
	sq->array[0] = sq->array[sq->top];
	sq->top -= 1;
	
	// sift down
	int idx = 0; // start at the root
	int swap_idx = -1;

	int keep_checking = 1;
	while (keep_checking) {
        // calculate children positions
		int left_idx = 2 * idx + 1;
		int right_idx = left_idx + 1;

        // compare values of current, left child, and right child
		int val = sq->array[idx];
		int left_val = -1;
		int right_val = -1;
		if (left_idx <= sq->top)
			left_val = sq->array[left_idx];
		if (right_idx <= sq->top)
			right_val = sq->array[right_idx];
		
        // set swap position to the child whose value is larger, or to -1 if current is the largest
		if (left_val > val)
			swap_idx = left_idx;
		if (right_val > val)
			swap_idx = right_idx;
		if (swap_idx > -1 && left_val > right_val)
			swap_idx = left_idx;

        // only swap if at least one child is larger
		if (swap_idx >= 0) {
            // swap child and current
			int temp = sq->array[swap_idx];
			sq->array[swap_idx] = sq->array[idx];
			sq->array[idx] = temp;
            
            // do the same for the child
			idx = swap_idx;
			swap_idx = -1;
		} else {
			keep_checking = 0;
		}
	}

	return ret_val;
}

// guess the data structure for the next num_lines lines
void guess_structure(int num_lines) {
    
	// create data structures
	// size is the maximum possible number of inputs to be safe
    
    // don't need bottom for stack
	struct stack_queue stack;
	stack.array = malloc(sizeof(int[num_lines]));
	stack.top = -1;

	struct stack_queue queue;
	queue.array = malloc(sizeof(int[num_lines]));
	queue.top = -1;
	queue.bottom = 0;

    // don't need bottom for priority queue
	struct stack_queue pqueue;
	pqueue.array = malloc(sizeof(int[num_lines]));
	pqueue.top = -1;

    // start with assumption that all data structures are possible
	int stack_possible = 1;
	int queue_possible = 1;
	int pqueue_possible = 1;

	int num_elems = 0; // keep track of number of items in structures
	int is_possible = 1;

	for (int i = 0; i < num_lines; i++) {
		int action, value;
		scanf("%d %d", &action, &value);
		if (is_possible) { // continue, otherwise skip this because we know it is impossible
			if (action == 1) { // put in bag
				stack_push(&stack, value);
				queue_push(&queue, value);
				pqueue_push(&pqueue, value);
				num_elems += 1;
			} else { // take out of bag
				if (num_elems > 0) {
					// pop from each data structure to see if each is possible
					if (stack_possible && stack_pop(&stack) != value) {
						stack_possible = 0;
					}
					if (queue_possible && queue_pop(&queue) != value) {
						queue_possible = 0;
					}
					if (pqueue_possible && pqueue_pop(&pqueue) != value) {
						pqueue_possible = 0;
					}
					is_possible = stack_possible || queue_possible || pqueue_possible;
					num_elems -= 1;
				} else { // impossible
					is_possible = 0;
				}
			}
		}
	}

    // print result depending on possibility markers
	if (!is_possible) {
		printf("impossible\n");
	} else {
		if (stack_possible + queue_possible + pqueue_possible > 1) {
			printf("not sure\n");
		} else { // only one data structure is possible
			if (stack_possible)
				printf("stack\n");
			else if (queue_possible)
				printf("queue\n");
			else
				printf("priority queue\n");
		}
	}

	free(stack.array);
	free(queue.array);
	free(pqueue.array);
}

int main() {
	int num_lines;
	while (scanf("%d", &num_lines) != EOF) {
		guess_structure(num_lines);
	}
	return 0;
}
