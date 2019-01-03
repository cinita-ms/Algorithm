#include <stdio.h>

typedef struct _Node
{
    int data;
    Node *next;
} Node;

Node *reverse(Node *head)
{
    if (head == NULL || head->next == NULL)
        return head;

    Node *next = NULL;
    Node *prev = NULL;

    while (head != NULL)
    {
        next = head->next;
        head->next = prev;
        prev = head;
        head = next;
    }
    return prev;
}

Node *middle(Node *head)
{
    if (head == NULL || head->next == NULL)
        return head;

    Node *fast = head;
    Node *low = head;
    while (head != NULL && head->next != NULL)
    {
        fast = fast->next->next;
        low = low->next;
    }
    return low;
}

int is_cycled(Node *head)
{
    if (head == NULL || head->next == NULL)
        return 0;

    Node *fast = head;
    Node *low = head;
    while (head != NULL && head->next != NULL)
    {
        fast = fast->next->next;
        low = low->next;

        if (fast == low)
        {
            return 1;
        }
    }
    return 0;
}
