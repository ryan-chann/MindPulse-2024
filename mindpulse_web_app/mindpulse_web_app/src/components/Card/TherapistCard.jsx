import {Button, Image, Tag} from "antd";
import {SearchOutlined} from "@ant-design/icons";
import { Link } from 'react-router-dom';

const TherapistCard = ({therapistInfo}) => {

    const therapistCardStyle = {
        display: 'flex',
        flexDirection: 'column',
        width: '200px'
    }

    const fullCapacityTagStyle = {
        width: '120px',
        height: '22px',
        backgroundColor: '#FFF1F0',
        borderColor: '#FFA39E',
        color: '#F5222D',
        fontSize: '12px',
        fontFamily: 'Roboto',
        borderRadius: '4px',
        textAlign: 'center',
        margin: '0px 0px 5px 80px'
    };

    const openToNewClientTagStyle = {
        width: '120px',
        height: '22px',
        backgroundColor: '#F6FFED',
        borderColor: '#B7EB8F',
        color: '#52C41A',
        fontSize: '12px',
        fontFamily: 'Roboto',
        borderRadius: '4px',
        textAlign: 'center',
        margin: '0px 0px 5px 80px'
    };

    const therapistNameSpanStyle = {
        fontWeight: 'bold',
        fontFamily: 'Roboto',
        fontSize: '18px',
        color: '#000000',
        margin: '10px 0px 0px 0px'
    }

    const therapistTypeSpanStyle = {
        fontWeight: 'regular',
        fontFamily: 'Roboto',
        fontSize: '12px',
        color: '#000000',
        opacity: '45%'
    }

    const viewProfileButtonStyle = {
        fontSize: "12px",
        height: "22px",
        width: "103px",
        margin: '5px 0px 0px 0px',
        borderRadius: '0px'
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

    return(
        <div style={therapistCardStyle}>
            {!therapistInfo.available ? (<Tag style={fullCapacityTagStyle}>At full capacity</Tag>) : (<Tag style={openToNewClientTagStyle}>Open to new client</Tag>)}
            <Image width={200} height={200} preview={false} src={therapistInfo.imageUrl}/>
            <span style={therapistNameSpanStyle}>{therapistInfo.name}</span>
            <span style={therapistTypeSpanStyle}>{getTherapistType(therapistInfo.type)}</span>
            <Link to={`/profile/${therapistInfo.id}`}>
                <Button type="primary" icon={<SearchOutlined />} style={viewProfileButtonStyle}>View Profile </Button>
            </Link>
        </div>
    )
}

export default TherapistCard;