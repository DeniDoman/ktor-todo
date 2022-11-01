import React from "react";
import "./todo-list-item.css";

const TodoListItem = ({id, text, isDone, onToggle, onDelete}) => {
    let classNames = "todo-list-item";
    let toggleBtnText = isDone ? "↻" : "✓"
    let toggleBtnType = isDone ? "btn-outline-warning" : "btn-outline-success"
    if (isDone) classNames += " done";

    return (<div className="d-flex justify-content-between align-items-center">
        <span className={classNames} data-test={"item-text"}>
          {text}
        </span>
        <div>
            <button className={`btn ${toggleBtnType} mx-3`} type="button" onClick={() => onToggle(id, isDone)} data-test={"item-toggle-button"}>
                {toggleBtnText}
            </button>
            <button className={"btn btn-outline-danger"} type="button" onClick={() => onDelete(id)} data-test={"item-delete-button"}>
                ✕
            </button>
        </div>
    </div>);
};

export default TodoListItem;
