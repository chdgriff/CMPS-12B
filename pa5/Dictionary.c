# include <stdio.h>
# include <stdlib.h>
# include <assert.h>
# include <string.h>
# include "Dictionary.h"

const int tableSize=101;

// Private functions

// rotate_left()
// rotate the bits in an unsigned int
unsigned int rotate_left(unsigned int value, int shift) {
    int sizeInBits = 8*sizeof(unsigned int);
    shift = shift & (sizeInBits - 1);
    if ( shift == 0 )
        return value;
    return (value << shift) | (value >> (sizeInBits - shift));
}

// pre_hash()
// turn a string into an unsigned int
unsigned int pre_hash(char* input) {
    unsigned int result = 0xBAE86554;
    while (*input) {
        result ^= *input++;
        result = rotate_left(result, 5);
    }
    return result;
}

// hash()
// turns a string into an int in the range 0 to tableSize-1
int hash(char* key){
    return pre_hash(key)%tableSize;
}

// Creates NodeObj
typedef struct NodeObj{
    char* key;
    char* value;
    struct NodeObj* next;
} NodeObj;
// Creates Node Alias
typedef NodeObj* Node;

// Node constructor
Node newNode(char* k, char* v) {
    Node n = malloc(sizeof(NodeObj));
    assert(n != NULL);
    n->key = k;
    n->value = v;
    n->next = NULL;
    return n;
}

// Node deconstructor
void deleteNode(Node* n) {
    free(*n);
    *n = NULL;
}

// ListObj
typedef struct ListObj {
    Node head;
    int numItems;
}ListObj;
// List alias for ListObj
typedef ListObj* List;

// List constructor
List newList(void) {
    List l = malloc(sizeof(ListObj));
    assert(l != NULL);
    l->head = NULL;
    l->numItems = 0;
    return l;
}

// List deconstructor
void deleteList(List* l) {
    free(*l);
    *l = NULL;
}

// Inserts new node at head of given list
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

// Returns value of matching key, NULL if not found
char* findValue(List l, char* k) {
    Node n = l->head;
    while (n != NULL) {
        if (strcmp(n->key, k) == 0) return n->value;
        n = n->next;
    }
    return NULL;
}

// Removes node of matching key, does nothing if not found
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

// Helper function to delete all nodes recursively
void deleteAllNodes(Node n) {
    if (n != NULL) {
        deleteAllNodes(n->next);
        deleteNode(&n);
    }
}

// Clears list of all nodes
void clearList(List l) {
    if (l != NULL) {
        Node n = l->head;
        deleteAllNodes(n);
        l->head = NULL;
        l->numItems = 0;
    }
}

// Prints list in format "key value"
void printList(FILE* out, List l) {
    Node n = l->head;
    while (n != NULL) {
        fprintf(out, "%s %s\n", n->key, n->value);
        n = n->next;
    }
}

typedef struct DictionaryObj {
    List* table;
    int numPairs;
} DictionaryObj;

// Start of dictionary.h implementation

typedef struct DictionaryObj* Dictionary;

Dictionary newDictionary() {
    Dictionary D = malloc(sizeof(DictionaryObj));
    D->table = malloc(sizeof(ListObj) * tableSize);
    for (int i = 0; i < tableSize; i++) D->table[i] = newList();
    
    D->numPairs = 0;
    return D;
}

void freeDictionary(Dictionary* pD) {
    if (pD != NULL && *pD != NULL) {
        makeEmpty(*pD);
        for (int i = 0; i < tableSize; i++) deleteList(&(*pD)->table[i]);
        free((*pD)->table);
        free(*pD);
        pD = NULL;
    }
}

int isEmpty(Dictionary D) {
    if (D == NULL) {
        fprintf(stderr, "Dictionary Error: calling isEmpty() on NULL Dictionary reference\n");
        exit(EXIT_FAILURE);
    }
    return D->numPairs == 0;
}

int size(Dictionary D) {
    if(D==NULL) {
        fprintf(stderr, "Dictionary Error: calling size() on NULL Dictionary reference\n");
        exit(EXIT_FAILURE);
    }
    return D->numPairs;
}

char* lookup(Dictionary D, char* k) {
    if (D == NULL) {
        fprintf(stderr, "Dictionary Error: calling lookup() on NULL Dictionary reference\n");
        exit(EXIT_FAILURE);
    }
    return findValue(D->table[hash(k)], k);
}

void insert(Dictionary D, char* k, char* v) {
    if(D == NULL) {
        fprintf(stderr, "Dictionary Error: calling insert() on NULL Dictionary reference\n");
        exit(EXIT_FAILURE);
    }
    if(lookup(D, k) != NULL) {
        fprintf(stderr, "Dictionary Error: cannot insert() duplicate key: \"%s\"\n", k);
        exit(EXIT_FAILURE);
    }
    insertNode(D->table[hash(k)], k, v);
    D->numPairs++;
}

void delete(Dictionary D, char* k) {
    if(D == NULL) {
        fprintf(stderr, "Dictionary Error: calling delete() on NULL Dictionary reference\n");
        exit(EXIT_FAILURE);
    }
    if(lookup(D, k) == NULL) {
        fprintf(stderr, "Dictionary Error: cannot delete() non-existent key: \"%s\"\n", k);
        exit(EXIT_FAILURE);
    }
    
    removeNode(D->table[hash(k)], k);
    D->numPairs--;
}

void makeEmpty(Dictionary D) {
    for (int i = 0; i < tableSize; i++) clearList(D->table[i]);
    D->numPairs = 0;
}

void printDictionary(FILE* out, Dictionary D) {
    List l = NULL;
    for (int i = 0; i < tableSize; i++) {
        l = D->table[i];
        printList(out, l);
    }
}






