# AI Email Writer

AI Email Writer is a web application that helps users draft professional emails using artificial intelligence. It integrates with the **Gemini API** to generate well-structured emails based on user input.  

This project consists of three main components:  
- **Frontend (`email-writer-react`)**: Built with **React + Vite**, using **Material UI** for UI components and **Axios** for API requests.  
- **Backend (`email-writer-sb`)**: A **Spring Boot** application that handles API requests and interacts with the Gemini API.  
- **Chrome Extension (`email-writer-ext`)**: A **JavaScript**-based Chrome extension to generate emails directly from the browser.  

---

## 🚀 Features  

- **AI-Powered Email Writing**: Uses the **Gemini API** to generate high-quality email drafts.  
- **Modern UI**: Built with **React (Vite)** and styled using **Material UI**.  
- **Robust Backend**: Developed using **Spring Boot**, ensuring efficient request handling.  
- **Chrome Extension**: Quick email drafting right from the browser.  
- **Environment Variable Support**: Easily configurable API key setup.  

---

## 🛠️ Tech Stack  

- **Frontend**: React, Vite, Material UI, Axios  
- **Backend**: Spring Boot  
- **Chrome Extension**: JavaScript  
- **AI Integration**: Gemini API  

---

### Videos of working 

https://github.com/user-attachments/assets/8878f6b3-0d32-4d0c-9ab0-d7df1858b72e

https://github.com/user-attachments/assets/6b1b5ffc-2376-4f26-abc8-845bf8bc47bb

https://github.com/user-attachments/assets/985e7ee4-e1af-403d-838a-8dcee2aef479

---
## ⚙️ Installation & Setup  

### Prerequisites  
Make sure you have the following installed:  
- **Node.js** (for frontend and extension)  
- **Java 11+** (for backend)  
- **Maven** (to build Spring Boot project) 
```
Frontend Setup (email-writer-react)
  cd email-writer-react
  npm install
  npm install @mui/material @emotion/react @emotion/styled axios
  npm run dev
Backend Setup (email-writer-sb)
  cd email-writer-sb
  mvn clean install
  mvn spring-boot:run
```

Chrome Extension Setup (email-writer-ext)
- Open Chrome and go to: chrome://extensions/
- Enable Developer mode (top right corner).
- Click Load unpacked and select the email-writer-ext folder.
- The extension will now be available in your browser.

🔑 Environment Variables
- To use the Gemini API, you need to set up an environment variable for your API key.
- Create a .env file in both the frontend (email-writer-react) and backend (email-writer-sb) directories.
- GEMINI_API_KEY=your_api_key_here
- After adding the key, restart your application.
