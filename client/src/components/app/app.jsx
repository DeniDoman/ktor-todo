import React, {useEffect, useState} from "react";
import AppHeader from "../app-header/app-header";
import TodoList from "../todo-list/todo-list";
import AddItem from "../add-item/add-item";
import KtorTodoApi from "../../api/ktor-todo-api";
import "./app.css";


const App = () => {
    const [toDoData, setToDoData] = useState([]);

    useEffect(() => {
        KtorTodoApi.getAllItems()
            .then(items => {
                setToDoData(items)
            })
    }, [])

    const onToggle = (id, isDone) => {
        KtorTodoApi.changeItemState(id, !isDone)
            .then(res => {
                if (!res) return
                setToDoData(toDoData.map(el => {
                    if (el.id === id) el.isDone = !el.isDone
                    return el
                }))
            })
    };

    const onDelete = id => {
        KtorTodoApi.deleteItem(id)
            .then(res => {
                if (!res) return
                setToDoData(toDoData.filter(el => el.id !== id))
            })
    };

    const onAdd = (text) => {
        KtorTodoApi.addNewItem(text)
            .then(res => {
                if (!res) return
                setToDoData(toDoData.concat([{id: res.id, text: res.text, isDone: res.isDone}]))
            })
    };

    return (
        <div className={"container pt-5"}>
            <AppHeader/>
            <AddItem onAdd={onAdd}/>
            <TodoList
                items={toDoData.slice()}
                onToggle={onToggle}
                onDelete={onDelete}
            />
        </div>);
};

export default App;
