import React from "react";
import TodoListItem from "../todo-list-item/todo-list-item";
import "./todo-list.css";

const TodoList = ({items, onToggle, onDelete}) => {
    const liItems = items
        .slice()
        .reverse()
        .map(item => {
            return (<li key={item.id} className="list-group-item" data-test={"todo-list-item"}>
                <TodoListItem
                    {...item}
                    onToggle={onToggle}
                    onDelete={onDelete}
                />
            </li>);
        });
    return <ul className="list-group todo-list" data-test={"todo-list"}>{liItems}</ul>;
};

export default TodoList;
