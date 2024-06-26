import TherapistProfileCard from "../components/Card/TherapistProfileCard";
import TherapistProfileDetailsCard from "../components/Card/TherapistProfileDetailsCard";

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

const TherapistInformation = () => {
    return(
        <div style={topDivStyle}>
            <div style={therapistProfileCardDivStyle}>
                <TherapistProfileCard />
            </div>

            <div style={therapistProfileDetailCardDivStyle}>
                <TherapistProfileDetailsCard />
            </div>
        </div>
    );
}

export default TherapistInformation;