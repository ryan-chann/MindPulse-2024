import TherapistProfileCard from "../components/Card/TherapistProfileCard";
import TherapistProfileDetailsCard from "../components/Card/TherapistProfileDetailsCard";
import {useEffect, useState} from "react";
import {fetchAllTherapistsInfo, fetchTherapistProfileById} from "../api/therapistApi";
import {useParams} from "react-router-dom";

const topDivStyle = {
    display: 'flex',
    flexDirection: 'row',
    width: '1170px'
}

const therapistProfileCardDivStyle = {
    margin:'82px 0px 0px 150px'
}

const therapistProfileDetailCardDivStyle = {
    margin:'119px 0px 0px 50px'
}


const TherapistProfile = () => {
    const {id } = useParams();
    const [therapistProfile, setTherapistProfile] = useState([]);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);

    useEffect(() => {
        const getTherapistProfile = async () => {
            try {
                const data = await fetchTherapistProfileById(id);
                setTherapistProfile(data);
            } catch (error) {
                setError(error);
            } finally {
                setLoading(false);
            }
        };

        getTherapistProfile();
    }, [id]);

    if (loading) return <p>Loading...</p>;
    if (error) return <p>Error: {error.message}</p>;

    return(
        <div style={topDivStyle}>
            <div style={therapistProfileCardDivStyle}>
                <TherapistProfileCard imageUrl={therapistProfile.therapistInfo.imageUrl} modeOfConduct={therapistProfile.therapistModeOfConduct} available={therapistProfile.therapistInfo.available} education={therapistProfile.therapistEducation}/>
            </div>

            <div style={therapistProfileDetailCardDivStyle}>
                <TherapistProfileDetailsCard name={therapistProfile.therapistInfo.name} therapistType={therapistProfile.therapistInfo.type} languages={therapistProfile.therapistLanguage.languages}  approaches={therapistProfile.therapistApproach.approaches} issues={therapistProfile.therapistAssistance.issues}/>
            </div>
        </div>
    );
}

export default TherapistProfile;