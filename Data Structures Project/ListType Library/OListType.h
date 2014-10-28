/*
 * OListype.cpp
 *
 *  Created on: Apr 13, 2013
 *      Author: administrator
 */
#ifndef OLISTTYPE_H
#define OLISTTYPE_H
#include "ListType.h"

template <class T>
class OListType:ListType<T>
{
public:
	OListType():ListType<T>(){};
	bool insert (const T&);
};

template <class T>
bool OListType<T>::insert(const T& item){
	  if(this -> empty()) {
		this -> head = new NodeType<T>;
		this -> head -> item = item;
		this -> tail = this -> head;
		this -> tail -> next = NULL;
	  }

	  else if(item <= this -> head -> item) {
		NodeType<T> *temp = new NodeType<T>;
		temp -> item = item;
		temp -> next = this -> head;
		this -> head = temp;
	  }

	  else if(item >= this -> tail -> item) {
		this -> tail -> next = new NodeType<T>;
		this -> tail = this -> tail -> next;
		this -> tail -> item = item;
		this -> tail -> next = NULL;
	  }

	  else {
		NodeType<T> *prev = NULL, *curr = this -> head, *temp = new NodeType<T>;
		while(curr != NULL && item > curr -> item) {
		  prev = curr;
		  curr = curr -> next;
		}
		temp -> next = curr;
		prev -> next = temp;
	  }
	  ++ this -> count;
	  return true;
}
#endif




