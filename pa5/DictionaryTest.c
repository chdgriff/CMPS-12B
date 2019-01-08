# include <stdio.h>
# include <stdlib.h>
# include <assert.h>
# include <string.h>
# include "Dictionary.h"

//Tests list for Hash Table
/*
typedef struct NodeObj{
    char* key;
    char* value;
    struct NodeObj* next;
} NodeObj;

typedef NodeObj* Node;

Node newNode(char* k, char* v) {
    Node n = malloc(sizeof(NodeObj));
    assert(n != NULL);
    n->key = k;
    n->value = v;
    n->next = NULL;
    return n;
}

void deleteNode(Node* n) {
    free(*n);
    *n = NULL;
}

typedef struct ListObj {
    Node head;
    int numItems;
}ListObj;

typedef ListObj* List;

List newList(void) {
    List l = malloc(sizeof(ListObj));
    assert(l != NULL);
    l->head = NULL;
    l->numItems = 0;
    return l;
}

void deleteList(List* l) {
    free(*l);
    *l = NULL;
}

void insertNode(List l, char* k, char* v) {
    if (l->numItems == 0) {
        l->head = newNode(k, v);
        l->numItems++;
    }
    else {
        Node n = newNode(k, v);
        n->next = l->head;
        l->head = n;
        l->numItems++;
    }
}

Node findNode(List l, char* k) {
    Node n = l->head;
    while (n != NULL) {
        if (strcmp(n->key, k) == 0) return n;
        n = n->next;
    }
    return NULL;
}

void removeNode(List l, char* k) {
    Node p = NULL;
    Node n = l->head;
    while (n != NULL) {
        if (strcmp(n->key, k) == 0) {
            if (p == NULL) {
                l->head = n->next;
                deleteNode(&n);
            }
            else {
                p->next = n->next;
                deleteNode(&n);
            }
        }
        else {
            p = n;
            n = n->next;
        }
    }
}

void deleteAllNodes(Node n) {
    if (n != NULL) {
        deleteAllNodes(n->next);
        deleteNode(&n);
    }
}

void clearList(List l) {
    Node n = l->head;
    deleteAllNodes(n);
    l->head = NULL;
    l->numItems = 0;
}

void printList(List l) {
    Node n = l->head;
    while (n != NULL) {
        printf("%s %s\n", n->key, n->value);
        n = n->next;
    }
}
*/

int main() {
    // Code to Test list
    /*
    List l = newList();
    
    insertNode(l, "one", "test1");
    insertNode(l, "two", "test2");
    insertNode(l, "three", "test3:");
    printList(l);
    
    Node n = findNode(l, "four");
    printf("%s\n", (n!=NULL ? n->value:"Not Found"));
    
    clearList(l);
    printList(l);

    deleteList(&l);
    */
    
    Dictionary d = newDictionary();
    printf("isEmpty(d): %s\n", (isEmpty(d) ? "true" : "false"));
    
    insert(d, "one", "test1");
    insert(d, "two", "test2");
    insert(d, "three", "test3");
    
    printf("isEmpty(d): %s\n", (isEmpty(d) ? "true" : "false"));
    printf("size(d) %d\n", size(d));
    printDictionary(stdout, d);
    
    printf("lookup(d, \"two\"): %s\n", (lookup(d, "two") != NULL ? lookup(d, "two") : "NULL"));
    
    delete(d, "three");
    printf("delete(d, \"three\"):\n");
    printf("size(d) %d\n", size(d));
    printDictionary(stdout, d);
    
    makeEmpty(d);
    printf("makeEmpty(d):\n");
    printf("size(d) %d\n", size(d));
    printDictionary(stdout, d);
    
    freeDictionary(&d);
    return (EXIT_SUCCESS);
}
