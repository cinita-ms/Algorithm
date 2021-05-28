//
//  linked_queue.c
//  AlgorithmC
//
//  Created by Zhiyuan Lv on 2021/5/29.
//

#include <stdlib.h>
#include "linked_queue.h"

typedef struct _LinkedQueueEntry LinkedQueueEntry;

struct _LinkedQueueEntry {
    Value value;
    LinkedQueueEntry *next;
};

struct _LinkedQueue {
    LinkedQueueEntry *head;
    LinkedQueueEntry *tail;
};

LinkedQueue *linked_queue(void)
{
    LinkedQueue *queue;
    queue = (LinkedQueue *)malloc(sizeof(LinkedQueueEntry));
    if (queue == NULL)
        return NULL;
    
    queue->head = NULL;
    queue->tail = NULL;
    return queue;
}

void linked_queue_free(LinkedQueue *queue)
{
    while (!linked_queue_is_empty(queue)) {
        linked_queue_dequeue(queue);
    }
    
    free(queue);
}

int linked_queue_enqueue(LinkedQueue *queue, Value value)
{
    LinkedQueueEntry *entry;
    entry = (LinkedQueueEntry *)malloc(sizeof(LinkedQueueEntry));
    if (entry == NULL)
        return 0;
    
    entry->value = value;
    entry->next = NULL;
    
    if (linked_queue_is_empty(queue)) {
        queue->head = queue->tail = entry;
    } else {
        queue->tail->next = entry;
        queue->tail = entry;
    }
    
    return 1;
}

Value linked_queue_dequeue(LinkedQueue *queue)
{
    if (linked_queue_is_empty(queue))
        return NULL;
    
    LinkedQueueEntry *head = queue->head;
    Value value = head->value;
    queue->head = head->next;
    free(head);
    return value;
}

int linked_queue_is_empty(LinkedQueue *queue)
{
    return queue->head == NULL;
}
