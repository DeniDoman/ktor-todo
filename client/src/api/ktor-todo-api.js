import axios from 'axios';

const V1_HELLO = "/v1/hello"
const V1_GET_ALL_ITEMS = "/v1/getAllItems"
const V1_ADD_NEW_ITEM = "/v1/addNewItem"
const V1_DELETE_ITEM = "/v1/deleteItem"
const V1_CHANGE_ITEM_STATE = "/v1/changeItemState"


export default class KtorTodoApi {
    static instance = axios.create({
        baseURL: 'http://0.0.0.0:8080/api'
    });

    static hello = async () =>
        this.instance.get(V1_HELLO)
            .then(res => res.data.toString())
            .catch(err => {
                throw new Error(`Couldn't GET ${V1_HELLO}, received: ${err.message}`)
            });

    static getAllItems = async () =>
        this.instance.get(V1_GET_ALL_ITEMS)
            .then(res => res.data.items)
            .catch(err => {
                throw new Error(`Couldn't GET ${V1_GET_ALL_ITEMS}, received: ${err.message}`)
            });

    static addNewItem = async (text) =>
        this.instance.post(V1_ADD_NEW_ITEM, {text: text})
            .then(res => res.data.item)
            .catch(err => {
                throw new Error(`Couldn't POST ${V1_ADD_NEW_ITEM}, received: ${err.message}`)
            });

    static deleteItem = async (id) =>
        this.instance.delete(`${V1_DELETE_ITEM}/${id}`)
            .then(res => res.data.result)
            .catch(err => {
                throw new Error(`Couldn't DELETE ${V1_DELETE_ITEM}, received: ${err.message}`)
            });

    static changeItemState = async (id, isDone) =>
        this.instance.patch(`${V1_CHANGE_ITEM_STATE}/${id}`, {isDone: isDone})
            .then(res => res.data.result)
            .catch(err => {
                throw new Error(`Couldn't PATCH ${V1_CHANGE_ITEM_STATE}, received: ${err.message}`)
            });
}