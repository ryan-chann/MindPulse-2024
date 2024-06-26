import {BrowserRouter, Route, Routes} from "react-router-dom";
import TherapistInformation from "../pages/TherapistInformation";

const AppRouter = () => {
    return (
        <BrowserRouter>
            <Routes>
                <Route path="/" element={<TherapistInformation/>} />
            </Routes>
        </BrowserRouter>
    );
};

export default AppRouter;