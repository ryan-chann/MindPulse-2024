import React from 'react';
import { Button } from "antd";
import {SearchOutlined} from "@ant-design/icons";

const ViewProfileButton = () => {
    const buttonStyle = {
        fontSize: "12px",
        height: "22px",
        width: "103px"
    }

    return (
        <Button type="primary" icon={<SearchOutlined />} style={buttonStyle}>
            View Profile
        </Button>
    );
};
export default ViewProfileButton;
