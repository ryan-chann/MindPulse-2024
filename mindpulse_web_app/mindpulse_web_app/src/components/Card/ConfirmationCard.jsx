import {Button, Col, Divider, message, Row, Upload} from "antd";
import React from "react";
import {PaymentDetailsCard, PriceCard} from "./index";
import {UploadOutlined} from "@ant-design/icons";
import dayjs from "dayjs";

const divStyle = {
    margin: '0px 0px 0px 250px'
}

const formLabelSpanStyle = {
    fontSize: '16px',
    fontWeight: 'bold',
}

const therapistTypeMap = {
    1: "Clinical Psychologist",
    2: "Counsellor",
    3: "Trainee"
};

const formatText = (text) => {
    switch (text) {
        case "inPerson":
            return "In-Person";
        case "online":
            return "Online";
        case "firstSession":
            return "First Session";
        case "returningSession":
            return "Returning Session";
        default:
            return text;
    }
};

const formatTime = (time) => {
    return dayjs(time, 'HH:mm').format('hh:mm A');
};


const ConfirmationCard = ({chooseAppointmentDetails, yourInformationDetails, therapistName, therapistType, handleFileUploadStatus}) => {
    const props = {
        name: 'file',
        action: 'http://localhost:8081/api/appointment/uploadFile',
        beforeUpload: (file) => {
            const newName = `${yourInformationDetails.fullName}_${chooseAppointmentDetails.selectedDate}_${chooseAppointmentDetails.selectedTime}_payment${file.name.substring(file.name.lastIndexOf('.'))}`;
            return new File([file], newName, {type: file.type});
        },
        maxCount: 1,
        onChange(info) {
            if (info.file.status !== 'uploading') {
                console.log(info.file, info.fileList);
            }
            if (info.file.status === 'done') {
                message.success(`${info.file.name} file uploaded successfully`);
                handleFileUploadStatus(true);
            } else if (info.file.status === 'error') {
                message.error(`${info.file.name} file upload failed.`);
                handleFileUploadStatus(false);
            } else if (info.file.status === 'removed'){
                handleFileUploadStatus(false);
            }
        },
    };

    return(
        <>
            <div style={divStyle}>
                <Row>
                    <Col span={24}>
                        <span style={{fontSize: '24px', fontWeight: 'bold', margin: '0px 0px 50px 195px'}}>Confirm Your Appointment</span>
                    </Col>
                </Row>
                <Row style={{margin: '60px 0px 0px 0px'}}>
                    <Col span={10}>
                        <Row style={{margin: '0px 0px 30px 0px'}}>
                            <span style={formLabelSpanStyle}>Therapist Details</span>
                            <Divider style={{margin: '10px 0px 7px 0px'}}/>
                                <Col span={12}>
                                    <span>Therapist: </span>
                                </Col>

                                <Col span={12} style={{textAlign: 'right', color: '#002766', opacity: '85%'}}>
                                    <span>{therapistName}</span>
                                </Col>

                                <Col span={12} style={{margin: '15px 0px 0px 0px'}}>
                                    <span>Therapist Type</span>
                                </Col>

                                <Col span={12} style={{textAlign: 'right', margin: '15px 0px 0px 0px', color: '#002766', opacity: '85%'}}>
                                    <span>{therapistTypeMap[therapistType]}</span>
                                </Col>
                        </Row>

                        <Row style={{margin: '0px 0px 30px 0px'}}>
                            <span style={formLabelSpanStyle}>Appointment Summary</span>
                            <Divider style={{margin: '10px 0px 7px 0px'}}/>
                            <Col span={12}>
                                <span>Mode of conduct: </span>
                            </Col>

                            <Col span={12} style={{textAlign: 'right', color: '#002766', opacity: '85%'}}>
                                <span>{formatText(chooseAppointmentDetails.mode)}</span>
                            </Col>

                            <Col span={12} style={{margin: '15px 0px 0px 0px'}}>
                                <span>Session</span>
                            </Col>

                            <Col span={12} style={{textAlign: 'right', margin: '15px 0px 0px 0px', color: '#002766', opacity: '85%'}}>
                                <span>{formatText(chooseAppointmentDetails.session)}</span>
                            </Col>

                            <Col span={12} style={{margin: '15px 0px 0px 0px'}}>
                                <span>Date: </span>
                            </Col>

                            <Col span={12} style={{textAlign: 'right', margin: '15px 0px 0px 0px', color: '#002766', opacity: '85%'}}>
                                <span>{chooseAppointmentDetails.selectedDate}</span>
                            </Col>

                            <Col span={12} style={{margin: '15px 0px 0px 0px'}}>
                                <span>Time</span>
                            </Col>

                            <Col span={12} style={{textAlign: 'right', margin: '15px 0px 0px 0px', color: '#002766', opacity: '85%'}}>
                                <span>{formatTime(chooseAppointmentDetails.selectedTime)}</span>
                            </Col>
                        </Row>

                        <Row>
                            <span style={formLabelSpanStyle}>Customer Details</span>
                            <Divider style={{margin: '10px 0px 7px 0px'}}/>
                            <Col span={12}>
                                <span>Full Name: </span>
                            </Col>

                            <Col span={12} style={{textAlign: 'right', color: '#002766', opacity: '85%'}}>
                                <span>{yourInformationDetails.fullName}</span>
                            </Col>

                            <Col span={12} style={{margin: '15px 0px 0px 0px'}}>
                                <span>Phone Number: </span>
                            </Col>

                            <Col span={12} style={{textAlign: 'right', margin: '15px 0px 0px 0px', color: '#002766', opacity: '85%'}}>
                                <span>{yourInformationDetails.phoneNumber}</span>
                            </Col>

                            <Col span={12} style={{margin: '15px 0px 0px 0px'}}>
                                <span>Email Address: </span>
                            </Col>

                            <Col span={12} style={{textAlign: 'right', margin: '15px 0px 0px 0px', color: '#002766', opacity: '85%'}}>
                                <span>{yourInformationDetails.emailAddress}</span>
                            </Col>

                            <Col span={12} style={{margin: '15px 0px 0px 0px'}}>
                                <span>NRIC</span>
                            </Col>

                            <Col span={12} style={{textAlign: 'right', margin: '15px 0px 0px 0px', color: '#002766', opacity: '85%'}}>
                                <span>{yourInformationDetails.nricNumber}</span>
                            </Col>
                        </Row>
                    </Col>

                    <Col span={14}>
                        <div style={{margin: '0px 0px 15px 70px'}}>
                            <PriceCard
                                session={chooseAppointmentDetails.session}
                                mode={chooseAppointmentDetails.mode}
                                s1={chooseAppointmentDetails.s1}
                                s2={chooseAppointmentDetails.s2}
                                taxRate={chooseAppointmentDetails.taxRate}
                                tax={chooseAppointmentDetails.tax}
                                totalPrice={chooseAppointmentDetails.totalPrice}
                            />
                        </div>

                        <div style={{margin: '50px 0px 40px 70px'}}>
                            <PaymentDetailsCard
                                totalPrice={chooseAppointmentDetails.totalPrice}
                                accountName="Mindpulse Enterprise"
                                accountNumber="1234-1234-1234"
                                bankName="Hong Leong Bank"
                                duitNowQrImageUrl="error"
                            />
                            <div style={{margin: '0px 0px 10px 0px'}}>
                                <span>Upload proof of payment (JPG, PNG & PDF only) </span>
                            </div>
                            <div>
                                <Upload {...props}>
                                    <Button icon={<UploadOutlined />}>Click to Upload</Button>
                                </Upload>
                            </div>
                        </div>
                    </Col>
                </Row>
            </div>
        </>
    )
}

export default ConfirmationCard;