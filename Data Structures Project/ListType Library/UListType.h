/*
 * UListType.cpp
 *
 *  Created on: Apr 13, 2013
 *      Author: administrator
 */

#ifndef ULISTTYPE_H
#define ULISTTYPE_H
#include "ListType.h"

template <class T>
class UListType:ListType<T>
{
public:
	UListType():ListType<T>(){};

	bool insert (const T&);
};

template <class T>
bool UListType<T>::insert(const T& item){
	if (!this->empty()){
		this->tail->next = new NodeType<T>;
		this->tail = this->tail->next;
	}
	else {
		this->tail = new NodeType<T>;
		this->head = this->tail;
	}
	this->tail->item = item;
	this->tail->next = NULL;
	++(this->count);
	return true;
}
#endif



