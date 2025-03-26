// Configuration for the application
const config = {
    // Get the backend URL from environment variable or fallback to localhost
    backendUrl: import.meta.env.VITE_BACKEND_URL || 'http://localhost:8080',
};

export default config; 