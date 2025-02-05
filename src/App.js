import React from "react";
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import Layout from './Component/Layout';
import LoginPage from "./Component/LoginPage"
function App() {
  return (
    <Router>
      <Routes>
        <Route path="/login" element={<LoginPage />} />
        <Route path="/*" element={<Layout />} /> 
      </Routes>
    </Router>
  );
}

export default App;