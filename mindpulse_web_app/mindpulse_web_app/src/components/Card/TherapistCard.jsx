import {Button, Image, Tag} from "antd";
import React, {useEffect} from "react";
import {SearchOutlined} from "@ant-design/icons";

const TherapistCard = ({available, therapistName, therapistType, therapistImage}) => {

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
            {!available ? (<Tag style={fullCapacityTagStyle}>At full capacity</Tag>) : (<Tag style={openToNewClientTagStyle}>Open to new client</Tag>)}
            <Image width={200} height={200} preview={false} src={therapistImage}/>
            <span style={therapistNameSpanStyle}>{therapistName}</span>
            <span style={therapistTypeSpanStyle}>{getTherapistType(therapistType)}</span>
            <Button type="primary" icon={<SearchOutlined />} style={viewProfileButtonStyle}>View Profile </Button>
        </div>
    )
}

export default TherapistCard;