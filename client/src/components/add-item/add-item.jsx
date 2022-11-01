import React, {useState} from "react";
import "./add-item.css";

const AddItem = ({onAdd}) => {
    const [input, setInput] = useState("");

    const onInputChange = e => {
        setInput(e.target.value);
    };

    const onFormSubmit = e => {
        e.preventDefault();
        if (input) {
            onAdd(input);
            setInput("");
        }
    };

    return (<div>
        <form className={"input-group mb-3"} onSubmit={onFormSubmit}>
            <input
                className={"form-control"}
                type="text"
                value={input}
                placeholder="Plan something!"
                onChange={onInputChange}
                data-test={"add-item-input"}
            />
            <button className={'btn btn-primary'} type="submit" data-test={"add-item-button"}>+</button>
        </form>
    </div>);
};

export default AddItem;
