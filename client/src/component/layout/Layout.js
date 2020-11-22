import React from "react";

const Layout= ({title, children}) => {
  return (
      <React.Fragment>
          <h1>{title}</h1>
          {children}
      </React.Fragment>
  );
};

export default Layout;