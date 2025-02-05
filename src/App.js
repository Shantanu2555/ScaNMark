import './App.css';
import {
  BrowserRouter as Router,
  Routes,
  Route
} from "react-router-dom";
import Faculty from "./components/Faculty";

function App() {
  return (
    <div className="App">
      <Router>
        <Routes>
          <Route path="/faculty" element={<Faculty />} />
          </Routes>
      </Router>
    </div>
  );
}

export default App;
