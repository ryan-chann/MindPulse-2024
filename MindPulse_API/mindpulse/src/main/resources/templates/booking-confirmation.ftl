<!DOCTYPE html>
<html>
    <body>
        <h1>We Have Received Your Booking! - MindPulse</h1>
        <p>Dear ${customerName},</p>

        <div style="margin: 0px 0px 0px 0px;">
            <p>Please be informed that we have successfully received your booking!</p>


            <p>Your Details:</p>
            <div style="margin: 0px 0px 0px 30px;">
                <p>Name: ${customerName}</p>
                <p>Phone Number: ${customerPhone}</p>
                <p>Email Address: ${customerEmail}</p>
                <p>NRIC: ${customerNRIC}</p>
            </div>

            <p>Your Therapist Details:</p>
            <div style="margin: 0px 0px 0px 30px;">
                <p>Name: ${therapistName}</p>
                <p>Therapist Type: ${therapistType}</p>
            </div>

            <p>Your Appointment Details:</p>
            <div style="margin: 0px 0px 0px 30px;">
                <p>Date: ${appointmentDate}</p>
                <p>Time:  ${appointmentTime}</p>
                <p>Mode of conduct: ${appointmentMode}</p>
                <p>Session: ${appointmentSession}</p>
            </div>
        </div>
    </body>
</html>