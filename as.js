var nodemailer = require('nodemailer');

var smtpTransport = nodemailer.createTransport("SMTP", {
    service: 'Gmail',
    auth: {
        user: 'tturrr10',
        pass: 'tnfdl3977'
    }
});

var mailOptions = {
    from: '송성광 <tturrr10@gmail.com>',
    to: 'tturrr19@gmail.com',
    subject: 'Nodemailer 테스트',
    text: '평문 보내기 테스트 '
};

smtpTransport.sendMail(mailOptions, function(error, response){

    if (error){
        console.log(error);
    } else {
        console.log("Message sent : " + response.message);
    }
    smtpTransport.close();
});
