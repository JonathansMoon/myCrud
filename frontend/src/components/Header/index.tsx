import React from 'react';
import { Nav, Navbar } from 'react-bootstrap';
import { Link } from 'react-router-dom';

const Header: React.FC = () => {
  return (
    <Navbar bg="dark" variant="dark" expand="lg">
        <Navbar.Brand href="#home">React-Bootstrap</Navbar.Brand>
        <Navbar.Toggle aria-controls="basic-navbar-nav" />
        <Navbar.Collapse id="basic-navbar-nav">
            <Nav className="mr-auto">
                <Nav.Item className="nav-link" as={Link} to="/">Index</Nav.Item>
                <Nav.Item className="nav-link" as={Link} to="/clientes">Clientes</Nav.Item>
            </Nav>
        </Navbar.Collapse>
    </Navbar>
  );
}

export default Header;