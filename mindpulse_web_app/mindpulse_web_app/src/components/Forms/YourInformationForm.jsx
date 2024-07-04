import {Col, Input, Row, Space, Typography} from "antd";
import React, {useEffect, useState} from "react";
const { Text } = Typography;

const YourInformationForm = ({yourInformationFormChange}) => {
    const [fullName, setFullName] = useState("");
    const [phoneNumber, setPhoneNumber] = useState("");
    const [emailAddress, setEmailAddress] = useState("");
    const [nricNumber, setNricNumber] = useState("");

    const [fullNameError, setFullNameError] = useState("");
    const [phoneNumberError, setPhoneNumberError] = useState("");
    const [emailAddressError, setEmailAddressError] = useState("");
    const [nricNumberError, setNricNumberError] = useState("");

    useEffect(() => {
        const details = {
            fullName,
            phoneNumber,
            emailAddress,
            nricNumber,
            fullNameError,
            phoneNumberError,
            emailAddressError,
            nricNumberError,
        };
        yourInformationFormChange(details);
    }, [fullName, phoneNumber, emailAddress, nricNumber]);


    const validateFullName = (name) => {
        const regex = /^[a-zA-Z\s]+$/;
        return regex.test(name);
    };

    const validatePhoneNumber = (number) => {
        const regex = /^\d{2}-\d{4}-\d{3,4}$/;
        return regex.test(number);
    };

    const validateEmailAddress = (email) => {
        const regex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
        return regex.test(email);
    };

    const validateNricNumber = (nric) => {
        const regex = /^\d{6}-\d{2}-\d{4}$/;
        return regex.test(nric);
    };

    const handleFullNameChange = (e) => {
        const value = e.target.value;
        setFullName(value);
        setFullNameError(!value ? "Full name is required." : validateFullName(value) ? "" : "Full name should only contain letters and spaces.");
    };

    const handlePhoneNumberChange = (e) => {
        let value = e.target.value.replace(/\D/g, ''); // Remove non-digit characters
        if (value.length > 2) {
            value = value.slice(0, 2) + '-' + value.slice(2);
        }
        if (value.length > 7) {
            value = value.slice(0, 7) + '-' + value.slice(7, 11);
        }
        setPhoneNumber(value);
        setPhoneNumberError(!value ? "Phone number is required." : validatePhoneNumber(value) ? "" : "Format: +60-12-1111-1111");
    };

    const handleEmailAddressChange = (e) => {
        const value = e.target.value;
        setEmailAddress(value);
        setEmailAddressError(!value ? "Email address is required." : validateEmailAddress(value) ? "" : "Invalid email address.");
    };

    const handleNricNumberChange = (e) => {
        let value = e.target.value.replace(/\D/g, ''); // Remove non-digit characters
        if (value.length > 6) {
            value = value.slice(0, 6) + '-' + value.slice(6);
        }
        if (value.length > 9) {
            value = value.slice(0, 9) + '-' + value.slice(9, 13);
        }
        setNricNumber(value);
        setNricNumberError(!value ? "NRIC number is required." : validateNricNumber(value) ? "" : "Invalid NRIC number.");
    };

    return (
        <>
            <Space direction="vertical" size="medium" style={{display: 'flex'}}>
                <Row>
                    <Col span={24} style={{margin: '0px 0px 50px 480px'}}>
                        <span style={{fontSize: '24px', fontWeight: 'bold'}}>Your Information</span>
                    </Col>
                </Row>

                <div style={{margin: '0px 0px 80px 200px', height:'300px'}}>
                    <div style={{margin: '0px 0px 20px 0px'}}>
                        <Row style={{margin: '0px 0px 5px 0px'}}>
                            <Col span={24}>
                                <span style={{fontSize: '14px', fontWeight: 'bold'}} value={fullName}>Full Name (as per NRIC)</span>
                            </Col>
                        </Row>

                        <Row>
                            <Col span={24}>
                                <Input placeholder="Enter your full name" style={{width: '750px'}} value={fullName} onChange={handleFullNameChange} />
                            </Col>
                            {fullNameError && <Text type="danger">{fullNameError}</Text>}
                        </Row>
                    </div>

                    <div style={{margin: '0px 0px 20px 0px'}}>
                        <Row style={{margin: '0px 0px 5px 0px'}}>
                            <Col span={24}>
                                <span style={{fontSize: '14px', fontWeight: 'bold'}}>Phone Number</span>
                            </Col>
                        </Row>

                        <Row>
                            <Col span={24}>
                                <Input addonBefore="+60" placeholder="Enter your phone number" style={{width: '750px'}} value={phoneNumber} onChange={handlePhoneNumberChange}/>
                            </Col>
                            {phoneNumberError && <Text type="danger">{phoneNumberError}</Text>}
                        </Row>
                    </div>

                    <div style={{margin: '0px 0px 20px 0px'}}>
                        <Row>
                            <Col span={24}>
                                <span style={{fontSize: '14px', fontWeight: 'bold'}}>Email Address</span>
                            </Col>
                        </Row>

                        <Row>
                            <Col span={24}>
                                <Input placeholder="Enter your email address" style={{width: '750px'}} value={emailAddress} onChange={handleEmailAddressChange}/>
                            </Col>
                            {emailAddressError && <Text type="danger">{emailAddressError}</Text>}
                        </Row>
                    </div>

                    <div style={{margin: '0px 0px 20px 0px'}}>
                        <Row>
                            <Col span={24}>
                                <span style={{fontSize: '14px', fontWeight: 'bold'}}>NRIC number</span>
                            </Col>
                        </Row>

                        <Row>
                            <Col span={24}>
                                <Input placeholder="Enter your NRIC number" style={{ width: '750px'}} value={nricNumber} onChange={handleNricNumberChange}/>
                            </Col>
                            {nricNumberError && <Text type="danger">{nricNumberError}</Text>}
                        </Row>
                    </div>
                </div>
            </Space>
        </>
    )
}

export default YourInformationForm;