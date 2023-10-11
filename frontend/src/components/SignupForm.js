import { Button, Form, Input, message, Modal } from "antd";
import React from "react";
import { LockOutlined, UserOutlined } from "@ant-design/icons";
import { signup } from "../utils";

class SignupForm extends React.Component {
  state = {
    displayModal: false,
  };

  handleCancel = () => {
    this.setState({
      displayModal: false,
    });
  };

  signupOnClick = () => {
    this.setState({
      displayModal: true,
    });
  };

  onFinish = (data) => {
    signup(data)
      .then(() => {
        this.setState({
          displayModal: false,
        });
        message.success(`Successfully signed up`); //this is a pop-up window
      })
      .catch((err) => {
        message.error(err.message);
      });
  };

  render = () => {
    return (
      <>
        {/* empty element */}
        <Button shape="round" type="primary" onClick={this.signupOnClick}>
          Register
        </Button>
        <Modal
          //   pop-up window
          title="Register"
          open={this.state.displayModal}
          // open decides when display model
          onCancel={this.handleCancel}
          footer={null}
          destroyOnClose={true}
        >
          <Form
            name="normal_register"
            initialValues={{ remember: true }}
            onFinish={this.onFinish}
            preserve={false}
          >
            <Form.Item
              name="email"
              rules={[{ required: true, message: "Please input your email!" }]}
            >
              <Input prefix={<UserOutlined />} placeholder="Email" />
            </Form.Item>
            <Form.Item
              name="password"
              rules={[
                { required: true, message: "Please input your password!" },
              ]}
            >
              <Input prefix={<LockOutlined />} placeholder="Password" />
            </Form.Item>
            <Form.Item
              name="firstName"
              rules={[
                { required: true, message: "Please input your first name!" },
              ]}
            >
              <Input placeholder="firstname" />
            </Form.Item>
            <Form.Item
              name="lastName"
              rules={[
                { required: true, message: "Please input your last name!" },
              ]}
            >
              <Input placeholder="lastname" />
            </Form.Item>

            <Form.Item>
              <Button type="primary" htmlType="submit">
                Register
              </Button>
            </Form.Item>
            {/* if all requirements satisfied, htmlType="submit" will execute onFinish() */}
          </Form>
        </Modal>
      </>
      // empty element closing tag
    );
  };
}

export default SignupForm;