import {Divider, Tag} from "antd";

const TherapistProfileDetailsCard = ({name, therapistType, languages, approaches, issues}) => {
    const divStyle = {
        display: 'flex',
        flexDirection: 'column',
        width: '820px'
    }

    const languageTagStyle = {
        backgroundColor: '#E6FFFB',
        color: '#13C2C2',
        borderColor: '#87E8DE',
        fontSize: '12px'
    }

    const unorderedListStyle = {
        fontSize: '12px',
    }

    const listStyle = {
        margin: '0px 0px 5px 0px'
    }

    const leftDivStyle = {
        width: '380px'
    }

    const bottomDivStyle = {
        display: 'flex',
        flexDirection: 'row'
    }

    const iCanHelpWithUnorderedListDivStyle = {
        display: 'flex',
        flexDirection: 'row'
    }

    const getTherapistType = (type) => {
        switch (type) {
            case 1:
                return "Clinical Psychologist";
            case 2:
                return "Counsellor";
            case 3:
                return "Trainee";
            default:
                return "Unknown";
        }
    }

    // Utility function to split array into chunks
    const splitArrayIntoSegments = (array, segmentSize) => {
        const result = [];
        for (let i = 0; i < array.length; i += segmentSize) {
            result.push(array.slice(i, i + segmentSize));
        }
        return result;
    };

    const issuesSegments = splitArrayIntoSegments(issues, 15);


    return (
        <div style={divStyle}>
            <span style={{fontWeight: 'bold', fontSize: '32px'}}>{name}</span>
            <Divider style={{margin: '13px 0px 12px 0px'}}/>
            <div style={bottomDivStyle}>
                <div style={leftDivStyle}>
                    <div style={{margin: '0px 0px 20px 0px'}}>
                        <span style={{
                            margin: '0px 18px 0px 0px',
                            fontWeight: 'bold',
                            fontSize: '14px'
                        }}>Therapist Type:</span>
                        <span style={{fontSize: '14px'}}>{getTherapistType(therapistType)}</span>
                    </div>

                    <div style={{margin: '0px 0px 20px 0px'}}>
                        <span
                            style={{margin: '0px 55px 0px 0px', fontWeight: 'bold', fontSize: '14px'}}>Language:</span>
                        {languages.map((language, index) => (
                            <Tag key={index} style={languageTagStyle}>{language}</Tag>
                        ))}
                    </div>

                    <div>
                        <span style={{fontWeight: 'bold', fontSize: '14px'}}>Approaches:</span>
                        <ul style={unorderedListStyle}>
                            {approaches.map((approach, index) => (
                                <li key={index} style={listStyle}>{approach}</li>
                            ))}
                        </ul>
                    </div>
                </div>


                <div style={{marginTop: '20px'}}>
                    <span style={{fontWeight: 'bold', fontSize: '14px'}}>I can help with:</span>
                    <div style={iCanHelpWithUnorderedListDivStyle}>
                        {issuesSegments.map((issuesSegment, index) => (
                            <ul key={index} style={unorderedListStyle}>
                                {issuesSegment.map((issue, index2) => (
                                    <li key={index2} style={listStyle}>{issue}</li>
                                ))}
                            </ul>
                        ))}
                    </div>
                </div>
            </div>
        </div>
    )
}
export default TherapistProfileDetailsCard
