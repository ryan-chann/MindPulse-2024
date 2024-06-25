import TherapistListing from "../pages/TherapistListing";
import {BrowserRouter, Route, Routes} from "react-router-dom";

const AppRouter = () => {
    return (
        <BrowserRouter>
            <Routes>
                <Route path="/" element={<TherapistListing />} />
            </Routes>
        </BrowserRouter>
    );
};

export default AppRouter;