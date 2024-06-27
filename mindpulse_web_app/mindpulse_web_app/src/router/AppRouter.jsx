import {BrowserRouter, Route, Routes} from "react-router-dom";

import TherapistProfile from "../pages/TherapistProfile";
import TherapistListing from "../pages/TherapistListing";

const AppRouter = () => {
    return (
        <BrowserRouter>
            <Routes>
                <Route path="/" element={<TherapistListing />} />
                <Route path="/profile/:id" element={<TherapistProfile />} />
            </Routes>
        </BrowserRouter>
    );
};

export default AppRouter;