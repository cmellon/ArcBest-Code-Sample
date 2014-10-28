/*
 * NodeType.h
 *
 *  Created on: Apr 13, 2013
 *      Author: administrator
 */
#ifndef NODETYPE_H
#define NODETYPE_H

template <class T>
struct NodeType
{
	 T item;
	NodeType<T> *next;
};
#endif




