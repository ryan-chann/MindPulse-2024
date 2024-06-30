import React, {useEffect, useState} from 'react';
import {DatePicker, Space, Flex, Radio, Col, Row, Button} from "antd";
import dayjs from "dayjs";
import {DoubleRightOutlined} from "@ant-design/icons";

const divStyle = {
    margin: '0px 0px 0px 250px'
}

const formDivStyle = {
    margin: '50px 0px 0px 0px',
}

const formLabelSpanStyle = {
    fontSize: '16px',
    fontWeight: 'bold',
}

const wrapperStyle = {
    margin: '10px 0px 0px 0px',
    width: 300,
};

const offDates = [
    '2024-07-01',
    '2024-07-05',
    '2024-07-10'
];

const workingDays = [1, 2, 3, 4, 5];


const ChooseAppointmentForm = () => {
    const dayjsOffDates = offDates.map(dateString => dayjs(dateString));

    const onChange = (date, dateString) => {
        console.log(date, dateString);
    };

    const disabledDates = (current) => {
        if (current && current < dayjs().startOf('day')) {
            return true;
        }
        if (!workingDays.includes(current.day())) {
            return true;
        }
        if (dayjsOffDates.some(date => date.isSame(current, 'day'))) {
            return true;
        }
        return false;
    };

    return (
        <>
            <div style={divStyle}>
                <Row>
                    <Col span={24}>
                        <span style={{fontSize: '24px', fontWeight: 'bold', margin: '0px 0px 50px 220px'}}>Choose Appointment</span>
                    </Col>
                </Row>

                <div style={formDivStyle}>
                    <Row style={{margin: "0px 0px 75px 0px"}}>
                        <Col span={12}>
                            <div style={{margin: '0px 0px 30px 0px'}}>
                                <span style={formLabelSpanStyle}>Mode of conduct</span>
                                <Flex vertical gap="middle" style={{margin: '10px 0px 0px 0px'}}>
                                    <Radio.Group defaultValue="inPerson">
                                        <Radio.Button value="inPerson">In-Person</Radio.Button>
                                        <Radio.Button value="online">Online</Radio.Button>
                                    </Radio.Group>
                                </Flex>
                            </div>

                            <div style={{margin: '0px 0px 35px 0px'}}>
                                <span style={formLabelSpanStyle}>Session</span>
                                <Flex vertical gap="middle" style={{margin: '10px 0px 0px 0px'}}>
                                    <Radio.Group defaultValue="firstSession">
                                        <Radio.Button value="firstSession">First session</Radio.Button>
                                        <Radio.Button value="returningSession">Returning session</Radio.Button>
                                    </Radio.Group>
                                </Flex>
                            </div>

                            <div>
                                <Space direction="vertical">
                                    <span style={formLabelSpanStyle}>Select Date</span>
                                    <DatePicker onChange={onChange} disabledDate={disabledDates} placeholder="No date selected" style={{width: '255px'}}/>
                                </Space>
                            </div>
                        </Col>

                        <Col span={12}>
                            <Space direction="vertical" size="small" style={{display: 'flex'}}>
                                <span style={formLabelSpanStyle}>Price</span>
                                <div style={{margin: '0px 0px 51px 30px'}}>
                                    <Space direction="vertical" size="small" style={{display: 'flex'}}>
                                        <Row>
                                            <Col span={12}>
                                                <span style={{fontSize: '12px'}}>Mental Health Screening:</span>
                                            </Col>
                                            <Col span={12}>
                                                <span style={{fontSize: '12px'}}>RM 30.00</span>
                                            </Col>
                                        </Row>
                                        <Row>
                                            <Col span={12}>
                                                <span style={{fontSize: '12px'}}>Therapy Session:</span>
                                            </Col>
                                            <Col span={12}>
                                                <span style={{fontSize: '12px'}}>RM 55.00</span>
                                            </Col>
                                        </Row>
                                        <Row>
                                            <Col span={12}>
                                                <span style={{fontSize: '12px'}}>Tax (8%):</span>
                                            </Col>
                                            <Col span={12}>
                                                <span style={{fontSize: '12px'}}>RM 6.80</span>
                                            </Col>
                                        </Row>
                                        <Row>
                                            <Col span={12}>
                                                <span style={{fontSize: '14px', fontWeight:'bold'}}>Total Price:</span>
                                            </Col>
                                            <Col span={12}>
                                                <span style={{fontSize: '14px', fontWeight:'bold'}}>RM 91.80</span>
                                            </Col>
                                        </Row>
                                    </Space>
                                </div>
                            </Space>

                            <Space direction="vertical" size="middle" style={{display: 'flex'}}>
                                <span style={formLabelSpanStyle}>Select Time</span>
                                <div>
                                    <Space direction="horizontal" size="small" style={{display: 'flex'}}>
                                        <Row gutter={[16, 16]}>
                                            <Col span={6}><Button size={"small"}>10:00 am</Button></Col>
                                            <Col span={6}><Button size={"small"}>11:00 am</Button></Col>
                                            <Col span={6}><Button size={"small"}>12:00 pm</Button></Col>
                                            <Col span={6}><Button size={"small"}>01:00 pm</Button></Col>
                                            <Col span={6}><Button size={"small"}>02:00 pm</Button></Col>
                                            <Col span={6}><Button size={"small"}>03:00 pm</Button></Col>
                                            <Col span={6}><Button size={"small"}>04:00 pm</Button></Col>
                                            <Col span={6}><Button size={"small"}>05:00 pm</Button></Col>
                                        </Row>
                                    </Space>
                                </div>
                            </Space>
                        </Col>
                    </Row>
                </div>
            </div>
        </>
    )
}

export default ChooseAppointmentForm;