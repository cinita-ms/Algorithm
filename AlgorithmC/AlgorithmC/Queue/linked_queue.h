//
//  linked_queue.h
//  AlgorithmC
//
//  Created by Zhiyuan Lv on 2021/5/29.
//

#ifndef linked_queue_h
#define linked_queue_h

#ifdef __cplusplus
extern "C" {
#endif

#include "basic.h"

typedef struct _LinkedQueue LinkedQueue;

LinkedQueue *linked_queue_new(void);
void linked_queue_free(LinkedQueue *queue);
int linked_queue_enqueue(LinkedQueue *queue, Value value);
Value linked_queue_dequeue(LinkedQueue *queue);
int linked_queue_is_empty(LinkedQueue *queue);

#ifdef __cplusplus
}
#endif

#endif /* linked_queue_h */
