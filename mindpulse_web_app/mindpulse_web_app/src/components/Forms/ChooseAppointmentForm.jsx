import React, {useEffect, useState} from 'react';
import {DatePicker, Space, Flex, Radio, Col, Row, Button, Typography} from "antd";
import dayjs from "dayjs";
import {PriceCard} from "../Card";
const { Text } = Typography;


const divStyle = {
    margin: '0px 0px 0px 250px'
}

const formDivStyle = {
    margin: '50px 0px 0px 0px',
    height: '380px'
}

const formLabelSpanStyle = {
    fontSize: '16px',
    fontWeight: 'bold',
}

const wrapperStyle = {
    margin: '10px 0px 0px 0px',
    width: 300,
};

const flexContainerStyle = {
    display: 'flex',
    alignItems: 'center',
    gap: '4px'
};


const ChooseAppointmentForm = ({s1, s2, taxRate, offDates, workingDays, workingStartTime, workingEndTime, unavailableSlots, chooseAppointmentFormChange}) => {
    const [mode, setMode] = useState("inPerson");
    const [session, setSession] = useState("firstSession");
    const dayjsOffDates = offDates.map(dateString => dayjs(dateString));
    const [selectedTime, setSelectedTime] = useState(null);
    const [selectedDate, setSelectedDate] = useState(null);

    const onChange = (date, dateString) => {
        setSelectedDate(date);
        setSelectedTime(null); // Reset selected time when date changes
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

    const generateTimeSlots = (start, end) => {
        let slots = [];
        let current = dayjs(start, 'HH:mm');
        const endDayjs = dayjs(end, 'HH:mm');

        while (current.isBefore(endDayjs) || current.isSame(endDayjs)) {
            slots.push(current.format('HH:mm'));
            current = current.add(2, 'hour');
        }

        return slots;
    };

    const timeSlots = selectedDate ? generateTimeSlots(workingStartTime, workingEndTime).filter(slot => {
        return !unavailableSlots.some(unavailableSlot => {
            const slotDateTime = selectedDate.format('YYYY-MM-DD') + 'T' + slot + ':00';
            return unavailableSlot === slotDateTime;
        });
    }) : [];

    const handleTimeSelect = (time) => {
        setSelectedTime(prevSelectedTime => prevSelectedTime === time ? null : time);
    };

    const getPrice = () => {
        const rateType = mode === "online" ? "OnlineRate" : "InPersonRate";
        let price = 0;

        if (session === "firstSession") {
            price += s1[rateType];
        }
        price += s2[rateType];

        const tax = price * taxRate;
        const totalPrice = price + tax;

        return {
            price,
            tax,
            totalPrice,
        };
    };

    const { price, tax, totalPrice } = getPrice();

    useEffect(() => {
        const details = {
            mode,
            session,
            selectedDate: selectedDate ? selectedDate.format('YYYY-MM-DD') : null,
            selectedTime,
            s1,
            s2,
            totalPrice,
            taxRate,
            tax,
        };
        chooseAppointmentFormChange(details);
    }, [mode, session, selectedDate, selectedTime, s1, s2, totalPrice, tax]);

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
                                    <Radio.Group defaultValue="inPerson" onChange={(e) => setMode(e.target.value)}>
                                        <Radio.Button value="inPerson">In-Person</Radio.Button>
                                        <Radio.Button value="online">Online</Radio.Button>
                                    </Radio.Group>
                                </Flex>
                            </div>

                            <div style={{margin: '0px 0px 35px 0px'}}>
                                <span style={formLabelSpanStyle}>Session</span>
                                <Flex vertical gap="middle" style={{margin: '10px 0px 0px 0px'}}>
                                    <Radio.Group defaultValue="firstSession" onChange={(e) => setSession(e.target.value)}>
                                        <Radio.Button value="firstSession">First session</Radio.Button>
                                        <Radio.Button value="returningSession">Returning session</Radio.Button>
                                    </Radio.Group>
                                </Flex>
                            </div>

                            <div>
                                <Space direction="vertical">
                                    <div style={flexContainerStyle}>
                                        <span style={formLabelSpanStyle}>Select Date</span>
                                        {!selectedDate && <Text type="danger">*</Text>}
                                    </div>
                                    <DatePicker onChange={onChange} disabledDate={disabledDates}  placeholder="No date selected" style={{width: '255px'}}/>
                                </Space>
                            </div>
                        </Col>

                        <Col span={12}>
                            <PriceCard
                                session={session}
                                mode={mode}
                                s1={s1}
                                s2={s2}
                                taxRate={taxRate}
                                price={price}
                                tax={tax}
                                totalPrice={totalPrice}
                            />

                            <Space direction="vertical" size="middle" style={{ display: 'flex', height: '100px' }}>
                                <div style={{margin: '50px 0px 0px 0px', display: 'flex', alignItems: 'center', gap: '4px'}}>
                                    <span style={formLabelSpanStyle}>Select Date</span>
                                    {!selectedTime && <Text type="danger">*</Text>}
                                </div>
                                <div style={{display: 'flex', flexWrap: 'wrap', gap: '10px'}}>
                                    {timeSlots.map((slot, index) => (
                                        <Button
                                            key={index}
                                            size="small"
                                            type={slot === selectedTime ? 'primary' : 'default'}
                                            onClick={() => handleTimeSelect(slot)}
                                        >
                                            {dayjs(slot, 'HH:mm').format('hh:mm A')}
                                        </Button>
                                    ))}
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