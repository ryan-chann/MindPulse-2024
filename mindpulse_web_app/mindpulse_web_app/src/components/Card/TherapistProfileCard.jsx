import {Image, Divider, Tag} from "antd";
import {SmileOutlined, WifiOutlined} from "@ant-design/icons";
import React from "react";

const TherapistProfileCard = ({imageUrl, modeOfConduct, available, education}) => {
    const divStyle = {
        display: 'flex',
        width: '300px',
        flexDirection: 'column'
    }

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
        margin: '0px 0px 15px 0px'
    };

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
        margin: '0px 0px 15px 0px'
    };

    const modeOfConductDivStyle = {
        display: 'inline-flex',
        margin: '10px 0px 10px 0px'
    }

    const modeOfConductTagStyle = {
        margin: '0px 0px 0px 5px'
    }


    return(
        <div style={divStyle}>
            {!available ? (<Tag style={fullCapacityTagStyle}>At full capacity</Tag>) : (<Tag style={openToNewClientTagStyle}>Open to new client</Tag>)}
            <Image width={300} height={300} preview={false} src={imageUrl}/>

            <div style={modeOfConductDivStyle}>
                <span style={{fontWeight: 'bold', margin:'2px 0px 0px 0px'}}>Mode of conduct:</span>
                {modeOfConduct.inPerson && (<Tag style={modeOfConductTagStyle} icon={<SmileOutlined />}>In-Person</Tag>)}
                {modeOfConduct.online && (<Tag style={modeOfConductTagStyle} icon={<WifiOutlined />}>Online</Tag>)}
            </div>

            <span style={{height:'22px', fontWeight: 'bold'}}>Education:</span>
            <span style={{height:'22px', padding: '5px 0px 0px 15px'}}>{education.highestEducation}</span>
            <span style={{height:'22px', padding: '2px 0px 5px 15px', opacity: '45%'}}>{education.universityName}, <span>{education.universityLocation}</span></span>
        </div>
    )
}

export default TherapistProfileCard;