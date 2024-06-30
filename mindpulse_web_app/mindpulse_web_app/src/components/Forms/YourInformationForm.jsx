import {Col, Input, Row, Space} from "antd";
import React from "react";

const YourInformationForm = () => {
    return (
        <>
            <Space direction="vertical" size="medium" style={{display: 'flex'}}>
                <Row>
                    <Col span={24} style={{margin: '0px 0px 50px 480px'}}>
                        <span style={{fontSize: '24px', fontWeight: 'bold'}}>Your Information</span>
                    </Col>
                </Row>

                <div style={{margin: '0px 0px 80px 200px'}}>
                    <div style={{margin: '0px 0px 20px 0px'}}>
                        <Row style={{margin: '0px 0px 5px 0px'}}>
                            <Col span={24}>
                                <span style={{fontSize: '14px', fontWeight: 'bold'}}>Full Name (as per NRIC)</span>
                            </Col>
                        </Row>

                        <Row>
                            <Col span={24}>
                                <Input placeholder="Enter your full name" style={{width: '750px'}}/>
                            </Col>
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
                                <Input addonBefore="+60" placeholder="Enter your phone number"  style={{width: '750px'}}/>
                            </Col>
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
                                <Input placeholder="Enter your email address"  style={{width: '750px'}}/>
                            </Col>
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
                                <Input placeholder="Enter your NRIC number" style={{ width: '750px'}}/>
                            </Col>
                        </Row>
                    </div>
                </div>
            </Space>
        </>
    )
}

export default YourInformationForm;