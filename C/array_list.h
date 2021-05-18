#ifndef CINITA_ALGO_H
#define CINITA_ALGO_H

#ifdef __cplusplus
extern "C" {
#endif

typedef void *ArrayListElement;

typedef struct 
{
    ArrayListElement *element;
    unsigned int size;

} ArrayList;

ArrayList *array_list_new(unsigned int size);
void array_list_free(ArrayList *array_list);
void array_list_append(ArrayList *array_list, ArrayListElement element);
int array_list_remove(ArrayList *array_list, unsigned int index);
int array_list_remove_all(ArrayList *array_list);

#ifdef __cplusplus
}
#endif

#endif
