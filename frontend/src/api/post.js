import request from './request'

export const getPosts = () => request.get('/posts')
export const getPost = (id) => request.get(`/posts/${id}`)
export const createPost = (data) => request.post('/posts', data)
export const deletePost = (id) => request.delete(`/posts/${id}`)
