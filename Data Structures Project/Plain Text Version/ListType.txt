/*
 * ListType.cpp
 *
 *  Created on: Apr 13, 2013
 *      Author: administrator
 */



#ifndef LISTTYPE_H
#define LISTTYPE_H
#include "NodeType.h"
#include <iostream>

template <class T>
class ListType
{
public:
	ListType();
	ListType(const ListType<T>&);
	virtual ~ListType();
	const ListType<T>& operator = (const ListType<T>&);
	virtual bool insert(const T&);
	bool erase();
	bool erase (const T&);
	bool find (const T&)const;
	size_t size()const;
	bool empty()const;


private:
	void destroy();
	friend std::ostream& operator << (std::ostream&, const ListType<T>&);

protected:
	NodeType<T> *head;
	NodeType<T> *tail;
	size_t count;
};

//head/tail null
//count = 0
//Initializes a new list with no items

/***********************************************************************
 * Purpose: To initialize a new
 * Postcondition: head and tail are null and count is set to zero
 *
 *
 ***********************************************************************/
template <class T>
ListType<T>::ListType(){
	head = NULL;
	tail = NULL;
	count = 0;
}

/***********************************************************************
 * Purpose: To initialize a new
 * Postcondition: head and tail are null and count is set to zero
 *
 *
 ***********************************************************************/
template <class T>
ListType<T>::ListType(const ListType<T>& src){
	count = src.count;
	NodeType<T> *temp = src.head;
	head = tail = NULL;
	if(temp != NULL){
		head = new NodeType <T>;
		head -> item = temp -> item;
		tail = head;
		temp = temp -> next;
		while (temp!=NULL){
			tail -> next = new NodeType<T>;
			tail = tail ->next;
			tail->item = temp -> item;
			temp = temp -> next;
		}
	}
}

/***********************************************************************
 * Purpose: To initialize a new
 * Postcondition: head and tail are null and count is set to zero
 *
 *
 ***********************************************************************/
template <class T>
ListType<T>::~ListType(){
	destroy();
}

/***********************************************************************
 * Purpose:
 * Postcondition: head and tail are null and count is set to zero
 *
 *
 ***********************************************************************/
template <class T>
const ListType<T>& ListType<T>::operator = (const ListType<T>& src){
	if (this!= &src){
		destroy();
		count = src.count;
		NodeType<T> *temp = src.head;
		head = tail = NULL;
		if(temp != NULL){
			head = new NodeType <T>;
			head -> item = temp -> item;
			tail = head;
			temp = temp -> next;
			while (temp!=NULL){
				tail -> next = new NodeType<T>;
				tail = tail ->next;
				tail->item = temp -> item;
				temp = temp -> next;
			}
		}
		tail->next = NULL;
	}
	return *this;
}

template <class U>
std::ostream& operator<<(std::ostream out, const ListType<U>& list){ // GOT RID OF FRIEND
	if(!list.empty()){
		NodeType<U> *temp = list.head;
		for(int i=0; i<list.size()&&temp!=NULL; ++i){
		out << temp.item;
		if (temp->next != NULL)out<<", ";
		temp = temp->next();
		}
		out << "\n";
	}
	return out;
}

/*****************************************************************************
 * Erase()
 * Purpose: To erase all nodes in a list
 * Postcondition: Memory is deallocated to all of the nodes.
 * The list will be empty. Head and Tail will be NULL. Count is 0.
 ****************************************************************************/
template <class T>
bool ListType<T>::erase(){
	if (empty())return false;
	destroy();
	return true;
}


template <class T>
bool ListType<T>::erase (const T& item){
	if (!empty()){
		NodeType<T> *curr = head;
		NodeType<T> *prev = NULL;
		while (curr!=NULL && curr->item != item){
			prev = curr;
			curr = curr->next;
		}
		if (curr!=NULL){
			if (prev!= NULL){
				prev->next = curr->next;
				if (prev ->next == NULL)tail = prev;//if we're deleting the last item
			}
				else {// we/re deleting the only item in the list
					head = curr->next;
					if(head == NULL)tail == NULL; //no items
				}
				delete curr;
				--count;
			}
		else return false;// if increment reaches end of list without finding item
		}
	return true;
	}


template <class T>
bool ListType<T>::find (const T& item)const{
	NodeType<T> *curr = head;
	while (curr!=NULL && curr->item!= item){
		curr = curr->next;
	}
	return curr!=NULL;
}

template <class T>
size_t ListType<T>::size()const{
return count;
}

template <class T>
bool ListType<T>::empty()const{
	return head == NULL;
}

template <class T>
void ListType<T>::destroy(){
	NodeType<T> *temp;
	while(!empty()){
		temp = head;
		head = head->next;
		delete temp;
	}
 tail = NULL;
 count = 0;
}



#endif



