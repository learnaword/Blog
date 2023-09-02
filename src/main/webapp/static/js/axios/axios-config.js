// axios-config.js

// 从LocalStorage获取token
const token = localStorage.getItem('accessToken');

// 配置全局的Axios实例
const request = axios.create({
    baseURL: '/', // 换成您的API的基本URL
    headers: {
        'Authorization': `Bearer ${token}`, // 使用Bearer令牌类型，根据您的实际情况更改
    },
});

// 添加请求拦截器，用于在每个请求中添加token
request.interceptors.request.use(
    (config) => {
        // 在请求发送前做一些处理
        return config;
    },
    (error) => {
        // 处理请求错误
        return Promise.reject(error);
    }
);

export default request; // 导出配置好的Axios实例
