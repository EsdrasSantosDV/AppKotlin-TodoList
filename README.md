# tasks-api

This repository contains an API to perform CRUD operation with tasks.

# Requirements

Python 3.6+

# Installation

To execute this application, a virtual environment is needed. You can create a virtual environment using venv module:

```
$ python -m venv venv
```

Then a venv folder is created. Activate the virtual environment using the following command:

On **Windows/MAC**:
```
$ ./venv/Scripts/activate
```
- If an permission error has occurred on Windows, execute this command on Powershell as administrator:
  ```
  $ Set-ExecutionPolicy -ExecutionPolicy AllSigned
  ```

On **Linux**:
```
$ source venv/bin/activate
```

After this, third-party packages must be installed. The file `requirements.txt` contains it. You can use pip to install packages.

```
$ pip install -r requirements.txt
```

# Run application

For local execution, `uvicorn` is recommended to it. Execute the following command:

```
$ uvicorn src.main:app --host $IP --port $PORT
```

*Replace $IP and the $PORT to desired ones*
